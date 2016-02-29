/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.jy.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.jy.entity.JyCustomer;
import com.thinkgem.jeesite.modules.jy.service.JyCustomerService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 客户进度Controller
 * @author sp
 * @version 2016-02-25
 */
@Controller
@RequestMapping(value = "${adminPath}/jy/jyCustomer")
public class JyCustomerController extends BaseController {

	@Autowired
	private JyCustomerService jyCustomerService;
	
	@ModelAttribute
	public JyCustomer get(@RequestParam(required=false) String id) {
		JyCustomer entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = jyCustomerService.get(id);
		}
		if (entity == null){
			entity = new JyCustomer();
		}
		return entity;
	}
	
	@RequiresPermissions("jy:jyCustomer:superView")
	@RequestMapping(value = {"list"})
	public String list(JyCustomer jyCustomer, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<JyCustomer> page = jyCustomerService.findPage(new Page<JyCustomer>(request, response), jyCustomer); 
		model.addAttribute("page", page);
		model.addAttribute("active","list");
		return "modules/jy/jyCustomerList";
	}
	
	@RequiresPermissions("jy:jyCustomer:view")
	@RequestMapping(value = {"listSelf", ""})
	public String listSelf(JyCustomer jyCustomer, HttpServletRequest request, HttpServletResponse response, Model model) {
		jyCustomer.setCurrentFollower(UserUtils.getUser());
		Page<JyCustomer> page = jyCustomerService.findPage(new Page<JyCustomer>(request, response), jyCustomer); 
		model.addAttribute("page", page);
		model.addAttribute("active","listSelf");
		return "modules/jy/jyCustomerList";
	}

	@RequiresPermissions("jy:jyCustomer:view")
	@RequestMapping(value = "form")
	public String form(JyCustomer jyCustomer, Model model) {
		model.addAttribute("jyCustomer", jyCustomer);
		return "modules/jy/jyCustomerForm";
	}

	@RequiresPermissions("jy:jyCustomer:edit")
	@RequestMapping(value = "save")
	public String save(JyCustomer jyCustomer, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, jyCustomer)){
			return form(jyCustomer, model);
		}
		jyCustomerService.save(jyCustomer);
		addMessage(redirectAttributes, "保存客户进度成功");
		return "redirect:"+Global.getAdminPath()+"/jy/jyCustomer/?repage";
	}
	
	@RequiresPermissions("jy:jyCustomer:edit")
	@RequestMapping(value = "delete")
	public String delete(JyCustomer jyCustomer, RedirectAttributes redirectAttributes) {
		jyCustomerService.delete(jyCustomer);
		addMessage(redirectAttributes, "删除客户进度成功");
		return "redirect:"+Global.getAdminPath()+"/jy/jyCustomer/?repage";
	}

}