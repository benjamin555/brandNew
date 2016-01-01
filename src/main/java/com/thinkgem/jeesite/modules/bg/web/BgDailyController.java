/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bg.web;

import java.util.Date;

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
import com.thinkgem.jeesite.modules.bg.entity.BgDaily;
import com.thinkgem.jeesite.modules.bg.service.BgDailyService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 日报生成Controller
 * @author sp
 * @version 2015-12-22
 */
@Controller
@RequestMapping(value = "${adminPath}/bg/bgDaily")
public class BgDailyController extends BaseController {

	@Autowired
	private BgDailyService bgDailyService;
	
	@ModelAttribute
	public BgDaily get(@RequestParam(required=false) String id) {
		BgDaily entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bgDailyService.get(id);
		}
		if (entity == null){
			entity = new BgDaily();
			entity.setRiDate(new Date());
		}
		return entity;
	}
	
	@RequiresPermissions("bg:bgDaily:view")
	@RequestMapping(value = {"list", ""})
	public String list(BgDaily bgDaily, HttpServletRequest request, HttpServletResponse response, Model model) {
		boolean isManager = UserUtils.getUser().getRoleNames().contains("本公司管理员");
		if(!isManager){
			bgDaily.setCreateBy(UserUtils.getUser());
		}
		Page<BgDaily> page = bgDailyService.findPage(new Page<BgDaily>(request, response), bgDaily); 
		model.addAttribute("page", page);
		return "modules/bg/bgDailyList";
	}

	@RequiresPermissions("bg:bgDaily:view")
	@RequestMapping(value = "form")
	public String form(BgDaily bgDaily, Model model) {
		
		if (bgDaily.getCreateBy()==null) {
			bgDaily.setCreateBy(UserUtils.getUser());
		}
		
		model.addAttribute("bgDaily", bgDaily);
		return "modules/bg/bgDailyForm";
	}

	@RequiresPermissions("bg:bgDaily:edit")
	@RequestMapping(value = "save")
	public String save(BgDaily bgDaily, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bgDaily)){
			return form(bgDaily, model);
		}
		bgDailyService.save(bgDaily);
		addMessage(redirectAttributes, "保存日报成功");
		return "redirect:"+Global.getAdminPath()+"/bg/bgDaily/?repage";
	}
	
	@RequiresPermissions("bg:bgDaily:edit")
	@RequestMapping(value = "delete")
	public String delete(BgDaily bgDaily, RedirectAttributes redirectAttributes) {
		bgDailyService.delete(bgDaily);
		addMessage(redirectAttributes, "删除日报成功");
		return "redirect:"+Global.getAdminPath()+"/bg/bgDaily/?repage";
	}

}