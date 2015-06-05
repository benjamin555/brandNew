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
import com.thinkgem.jeesite.modules.bg.entity.BgExpress;
import com.thinkgem.jeesite.modules.bg.service.BgExpressService;

/**
 * 快件生成Controller
 * @author sp
 * @version 2015-06-06
 */
@Controller
@RequestMapping(value = "${adminPath}/bg/bgExpress")
public class BgExpressController extends BaseController {

	@Autowired
	private BgExpressService bgExpressService;
	
	@ModelAttribute
	public BgExpress get(@RequestParam(required=false) String id) {
		BgExpress entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bgExpressService.get(id);
		}
		if (entity == null){
			entity = new BgExpress();
		}
		return entity;
	}
	
	@RequiresPermissions("bg:bgExpress:view")
	@RequestMapping(value = {"list", ""})
	public String list(BgExpress bgExpress, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BgExpress> page = bgExpressService.findPage(new Page<BgExpress>(request, response), bgExpress); 
		model.addAttribute("page", page);
		return "modules/bg/bgExpressList";
	}

	@RequiresPermissions("bg:bgExpress:view")
	@RequestMapping(value = "form")
	public String form(BgExpress bgExpress, Model model) {
		model.addAttribute("bgExpress", bgExpress);
		return "modules/bg/bgExpressForm";
	}

	@RequiresPermissions("bg:bgExpress:edit")
	@RequestMapping(value = "save")
	public String save(BgExpress bgExpress, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bgExpress)){
			return form(bgExpress, model);
		}
		bgExpressService.save(bgExpress);
		addMessage(redirectAttributes, "保存快件成功");
		return "redirect:"+Global.getAdminPath()+"/bg/bgExpress/?repage";
	}
	
	@RequiresPermissions("bg:bgExpress:del")
	@RequestMapping(value = "delete")
	public String delete(BgExpress bgExpress, RedirectAttributes redirectAttributes) {
		bgExpressService.delete(bgExpress);
		addMessage(redirectAttributes, "删除快件成功");
		return "redirect:"+Global.getAdminPath()+"/bg/bgExpress/?repage";
	}

}