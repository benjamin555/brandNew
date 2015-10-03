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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.bg.entity.BgCaseFollow;
import com.thinkgem.jeesite.modules.bg.service.BgCaseFollowService;

/**
 * 案例进度生成Controller
 * @author sp
 * @version 2015-10-03
 */
@Controller
@RequestMapping(value = "${adminPath}/bg/bgCaseFollow")
public class BgCaseFollowController extends BaseController {

	@Autowired
	private BgCaseFollowService bgCaseFollowService;
	
	@ModelAttribute
	public BgCaseFollow get(@RequestParam(required=false) String id) {
		BgCaseFollow entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bgCaseFollowService.get(id);
		}
		if (entity == null){
			entity = new BgCaseFollow();
		}
		return entity;
	}
	
	@RequiresPermissions("bg:bgCaseFollow:view")
	@RequestMapping(value = {"list", ""})
	public String list(BgCaseFollow bgCaseFollow, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BgCaseFollow> page = bgCaseFollowService.findPage(new Page<BgCaseFollow>(request, response), bgCaseFollow); 
		model.addAttribute("page", page);
		return "modules/bg/bgCaseFollowList";
	}

	@RequiresPermissions("bg:bgCaseFollow:view")
	@RequestMapping(value = "form")
	public String form(BgCaseFollow bgCaseFollow, Model model) {
		model.addAttribute("bgCaseFollow", bgCaseFollow);
		return "modules/bg/bgCaseFollowForm";
	}

	@RequiresPermissions("bg:bgCaseFollow:edit")
	@RequestMapping(value = "save")
	public String save(BgCaseFollow bgCaseFollow, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bgCaseFollow)){
			return form(bgCaseFollow, model);
		}
		bgCaseFollowService.save(bgCaseFollow);
		addMessage(redirectAttributes, "保存案例进度成功");
		String url = "/bg/bgCaseFollow/?repage";
		url+="&caseId="+bgCaseFollow.getCaseId();
		return "redirect:"+Global.getAdminPath()+url;
	}
	
	@RequiresPermissions("bg:bgCaseFollow:edit")
	@RequestMapping(value = "delete")
	public String delete(BgCaseFollow bgCaseFollow, RedirectAttributes redirectAttributes) {
		bgCaseFollowService.delete(bgCaseFollow);
		addMessage(redirectAttributes, "删除案例进度成功");
		return "redirect:"+Global.getAdminPath()+"/bg/bgCaseFollow/?repage";
	}

}