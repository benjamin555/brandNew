package com.thinkgem.jeesite.modules.quartz;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thinkgem.jeesite.modules.mail.domain.MailVO;

/**
* @author 陈嘉镇
* @version 创建时间：2015-10-5 上午10:08:41
*/
@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-spring-context.xml"})
public class SchedulerFacadeTest {
	
	@Autowired
	private SchedulerFacade schedulerFacade;
	
	@Test
	public void testAddMailJob() throws Exception {
		Date date = new Date();
		MailVO vo = new MailVO();
		vo.setToAddress("a114807134@163.com");
		vo.setSubject("test");
		vo.setContent("nihao");
		MailJob job = new MailJob(vo );
		schedulerFacade.addMailJob(job , date );
		Thread.sleep(5000);
	}

}
