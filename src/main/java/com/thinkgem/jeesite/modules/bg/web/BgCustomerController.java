/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bg.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.bg.entity.BgContacter;
import com.thinkgem.jeesite.modules.bg.entity.BgCustomer;
import com.thinkgem.jeesite.modules.bg.service.BgCustomerService;

/**
 * 客户生成Controller
 * @author sp
 * @version 2015-05-30
 */
@Controller
@RequestMapping(value = "${adminPath}/bg/bgCustomer")
public class BgCustomerController extends BaseController {

	@Autowired
	private BgCustomerService bgCustomerService;
	
	@ModelAttribute
	public BgCustomer get(@RequestParam(required=false) String id) {
		BgCustomer entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bgCustomerService.get(id);
		}
		if (entity == null){
			entity = new BgCustomer();
		}
		return entity;
	}
	
	@RequiresPermissions("bg:bgCustomer:view")
	@RequestMapping(value = {"list", ""})
	public String list(BgCustomer bgCustomer, HttpServletRequest request, HttpServletResponse response, Model model) {
		String name = request.getParameter("bgCustomer.name");
		if(StringUtils.isNotBlank(name)){
			bgCustomer.setName(name);
		}
		Page<BgCustomer> page = bgCustomerService.findPage(new Page<BgCustomer>(request, response), bgCustomer); 
		model.addAttribute("page", page);
		return "modules/bg/bgCustomerList";
	}

	@RequiresPermissions("bg:bgCustomer:view")
	@RequestMapping(value = "form")
	public String form(BgCustomer bgCustomer, Model model) {
		model.addAttribute("bgCustomer", bgCustomer);
		return "modules/bg/bgCustomerForm";
	}
	
	@RequiresPermissions("bg:bgCustomer:detail")
	@RequestMapping(value = "detail")
	public String detail(BgCustomer bgCustomer, Model model) {
		model.addAttribute("bgCustomer", bgCustomer);
		return "modules/bg/bgCustomerDetail";
	}

	@RequiresPermissions("bg:bgCustomer:edit")
	@RequestMapping(value = "save")
	public String save(BgCustomer bgCustomer, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bgCustomer)){
			return form(bgCustomer, model);
		}
		bgCustomerService.save(bgCustomer);
		addMessage(redirectAttributes, "保存客户成功");
		return "redirect:"+Global.getAdminPath()+"/bg/bgCustomer/?repage";
	}
	
	@RequiresPermissions("bg:bgCustomer:del")
	@RequestMapping(value = "delete")
	public String delete(BgCustomer bgCustomer, RedirectAttributes redirectAttributes) {
		bgCustomerService.delete(bgCustomer);
		addMessage(redirectAttributes, "删除客户成功");
		return "redirect:"+Global.getAdminPath()+"/bg/bgCustomer/?repage";
	}
	
	@RequiresPermissions("bg:bgCustomer:view")
	@RequestMapping(value = "findContacters")
	@ResponseBody
	public String findContacters(String bgCustomerId) {
		List<BgContacter> cs = bgCustomerService.findContacters(bgCustomerId);
		return JsonMapper.toJsonString(cs);
	}

	@RequiresPermissions("bg:bgCustomer:listHelp")
	@RequestMapping(value = {"listHelp"})
	public String listHelp(BgCustomer bgCustomer, HttpServletRequest request, HttpServletResponse response, Model model) {
		String name = request.getParameter("bgCustomer.name");
		if(StringUtils.isNotBlank(name)){
			bgCustomer.setName(name);
		}
		Page<BgCustomer> page = bgCustomerService.findPage(new Page<BgCustomer>(request, response), bgCustomer);
		model.addAttribute("page", page);
		return "modules/bg/bgCustomerListHelp";
	}

	@RequiresPermissions("bg:bgCustomer:self")
	@RequestMapping(value = {"self"})
	public String self(BgCustomer bgCustomer, HttpServletRequest request, HttpServletResponse response, Model model) {
		String name = request.getParameter("bgCustomer.name");
		if(StringUtils.isNotBlank(name)){
			bgCustomer.setName(name);
		}
		bgCustomer.setBusinessMan(UserUtils.getUser().getName());
		Page<BgCustomer> page = bgCustomerService.findPageByBusinessMan(new Page<BgCustomer>(request, response), bgCustomer);
		model.addAttribute("page", page);
		return "modules/bg/bgCustomerListSelf";
	}

	@RequiresPermissions("bg:bgCustomer:self")
	@RequestMapping(value = "selfForm")
	public String selfForm(BgCustomer bgCustomer, Model model) {
		model.addAttribute("bgCustomer", bgCustomer);
		return "modules/bg/bgCustomerSelfForm";
	}

}