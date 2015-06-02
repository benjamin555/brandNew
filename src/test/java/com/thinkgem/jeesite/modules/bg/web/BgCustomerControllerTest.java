package com.thinkgem.jeesite.modules.bg.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import com.thinkgem.jeesite.modules.bg.entity.BgCustomer;

/**
* @author 陈嘉镇
* @version 创建时间：2015-6-1 下午11:09:55
*/
@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-spring-context.xml"})
public class BgCustomerControllerTest {

	@Autowired
	private BgCustomerController controller;
	@Test
	public void testList() throws Exception {
		Model m = new BindingAwareModelMap();
		controller.list(new BgCustomer(), new MockHttpServletRequest(), new MockHttpServletResponse(),m );
	}
}
