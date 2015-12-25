/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bg.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;

/**
 * 日报生成Controller
 * @author sp
 * @version 2015-12-22
 */
@Controller
@RequestMapping(value = "${adminPath}/bg/bgLottery")
public class BgLotteryController extends BaseController {
	
	@RequestMapping(value = {"","{personCnt}"})
	public String list(@PathVariable("personCnt")Integer personCnt, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (personCnt==null) {
			personCnt = 500;
		}
		model.addAttribute("personCnt", personCnt);
		return "modules/bg/lottery";
	}


}