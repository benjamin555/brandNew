package com.thinkgem.jeesite.modules.quartz;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.quartz.impl.StdScheduler;
import org.quartz.spi.MutableTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.modules.quartz.utils.CommonUtils;

/**
* @author 陈嘉镇
* @version 创建时间：2015-10-5 上午9:28:58
*/
@Component
public class SchedulerFacade {

	private static String JOB_GROUP_NAME = "ddlib";
	private static String TRIGGER_GROUP_NAME = "ddlibTrigger";
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private StdScheduler sf;
	
	public void addMailJob(MailJob job ,Date date) {
		logger.info("job:{},date:{}",job,date);
		addJob(job, CommonUtils.getCron(date));
	}

	private void addJob(Job job, String cronExpression) {
		try {
			addJob(job.toString(), null, job.toString(), null, job, cronExpression);
		} catch (SchedulerException e) {
			logger.error("",e);
		} catch (ParseException e) {
			logger.error("",e);
		}
	}

	/** 
	    * 添加一个定时任务
	    * @param jobName 任务名
	    * @param jobGroupName 任务组名
	    * @param triggerName 触发器名
	    * @param triggerGroupName 触发器组名
	    * @param job     任务
	    * @param cronExpression    时间设置，参考quartz说明文档
	    */
	private void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Job job,
			String cronExpression) throws SchedulerException, ParseException {
		if (StringUtils.isBlank(jobGroupName)) {
			jobGroupName = JOB_GROUP_NAME;
		}
		if (StringUtils.isBlank(triggerGroupName)) {
			triggerGroupName = TRIGGER_GROUP_NAME;
		}
		Scheduler sched = sf;
		JobDataMap newJobDataMap = new JobDataMap();
		if (job instanceof MailJob) {
			newJobDataMap.put("mailVO", ((MailJob)job).getMailVO());
		}
		JobDetail jobDetail = JobBuilder.newJob(job.getClass()).withIdentity(jobName, jobGroupName).usingJobData(newJobDataMap ).build();//任务名，任务组，任务执行类
		TriggerKey key = new TriggerKey(cronExpression);
		MutableTrigger buildT = CronScheduleBuilder.cronSchedule(cronExpression).build();
		buildT.setKey(key);
		sched.scheduleJob(jobDetail, buildT);
		//启动
		if (!sched.isShutdown()) {
			sched.start();
		}
	}

}
