package com.thinkgem.jeesite.modules.mail;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thinkgem.jeesite.common.utils.FileUtils;

/**
* @author 陈嘉镇
* @version 创建时间：2015-10-3 下午2:29:43
*/
@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-spring-context.xml"})
public class MailFacadeTest {
	
	@Autowired
	private MailFacade mailFacade;

	
	@Test
	public void testSendMail() throws Exception {
		File f = new File("src/test/resources/demo.html");
		String readFileToString = FileUtils.readFileToString(f,"utf-8");
		System.out.println(readFileToString);
		mailFacade.sendMail( "benjamin555@foxmail.com",
				"nihao", readFileToString);

	}

}

