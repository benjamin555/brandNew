/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bg.web;

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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.bg.entity.BgCase;
import com.thinkgem.jeesite.modules.bg.service.BgCaseService;

/**
 * 单表生成Controller
 * @author sp
 * @version 2015-05-30
 */
@Controller
@RequestMapping(value = "${adminPath}/bg/bgCase")
public class BgCaseController extends BaseController {

	@Autowired
	private BgCaseService bgCaseService;
	
	@ModelAttribute
	public BgCase get(@RequestParam(required=false) String id) {
		BgCase entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bgCaseService.get(id);
		}
		if (entity == null){
			entity = new BgCase();
		}
		return entity;
	}
	
	@RequiresPermissions("bg:bgCase:view")
	@RequestMapping(value = {"list", ""})
	public String list(BgCase bgCase, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BgCase> page = bgCaseService.findPage(new Page<BgCase>(request, response), bgCase); 
		model.addAttribute("page", page);
		return "modules/bg/bgCaseList";
	}

	@RequiresPermissions("bg:bgCase:view")
	@RequestMapping(value = "form")
	public String form(BgCase bgCase, Model model) {
		model.addAttribute("bgCase", bgCase);
		return "modules/bg/bgCaseForm";
	}

	@RequiresPermissions("bg:bgCase:edit")
	@RequestMapping(value = "save")
	public String save(BgCase bgCase, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bgCase)){
			return form(bgCase, model);
		}
		bgCaseService.save(bgCase);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/bg/bgCase/?repage";
	}
	
	@RequiresPermissions("bg:bgCase:edit")
	@RequestMapping(value = "delete")
	public String delete(BgCase bgCase, RedirectAttributes redirectAttributes) {
		bgCaseService.delete(bgCase);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/bg/bgCase/?repage";
	}

}