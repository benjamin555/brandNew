package com.thinkgem.jeesite.modules.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thinkgem.jeesite.common.utils.ComponentFactory;
import com.thinkgem.jeesite.modules.mail.MailFacade;
import com.thinkgem.jeesite.modules.mail.domain.MailVO;

/**
* @author 陈嘉镇
* @version 创建时间：2015-10-5 上午9:53:10
*/
public class MailJob implements Job {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private MailFacade mailFacade = (MailFacade) ComponentFactory.getBean("mailFacade");
	
	


	private MailVO mailVO;
	
	public MailJob() {
		super();
	}


	public MailJob(MailVO mailVO) {
		super();
		this.mailVO = mailVO;
	}



	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		init(context.getJobDetail().getJobDataMap());
		if (mailVO!=null) {
			mailFacade.sendMail(mailVO.getToAddress(), mailVO.getSubject(), mailVO.getContent());
		}else {
			logger.error("mail can't be null!");
		}
	}


	private void init(JobDataMap jobDataMap) {
		mailVO = (MailVO) jobDataMap.get("mailVO");
	}


	public MailVO getMailVO() {
		return mailVO;
	}


	public void setMailVO(MailVO mailVO) {
		this.mailVO = mailVO;
	}


	@Override
	public String toString() {
		return "MailJob [mailVO=" + mailVO + "]";
	}

}
