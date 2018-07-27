/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bg.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
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
import com.thinkgem.jeesite.modules.bg.entity.BgCase;
import com.thinkgem.jeesite.modules.bg.entity.BgContacter;
import com.thinkgem.jeesite.modules.bg.service.BgCaseService;
import com.thinkgem.jeesite.modules.bg.service.BgCustomerService;

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
	@Autowired
	private BgCustomerService bgCustomerService;
	
	
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
		List<BgCase> list = page.getList();
		for (BgCase bgCase2 : list) {
			List<BgContacter> cs = bgCustomerService.findContacters(bgCase2.getCustomerId());
			if (CollectionUtils.isNotEmpty(cs)) {
				List<String> ls = (List<String>) CollectionUtils.collect(cs, new Transformer() {
					@Override
					public Object transform(Object input) {
						return ((BgContacter)input).getName();
					}
				});
				bgCase2.setContacters(org.apache.commons.lang3.StringUtils.join(ls));
				logger.info("contacters:{}",bgCase2.getContacters());
			}
			
		}
		
		model.addAttribute("page", page);
		return "modules/bg/bgCaseList";
	}

	@RequiresPermissions("bg:bgCase:view")
	@RequestMapping(value = "form")
	public String form(BgCase bgCase, Model model) {
		bgCase.setBusinessManId(UserUtils.getUser().getName());
		model.addAttribute("bgCase", bgCase);
		return "modules/bg/bgCaseForm";
	}
	@RequiresPermissions("bg:bgCase:selfEdit")
	@RequestMapping(value = "selfForm")
	public String selfForm(BgCase bgCase, Model model) {
		bgCase.setBusinessManId(UserUtils.getUser().getName());
		model.addAttribute("bgCase", bgCase);
		return "modules/bg/bgCaseSelfForm";
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
	
	@RequiresPermissions("bg:bgCase:del")
	@RequestMapping(value = "delete")
	public String delete(BgCase bgCase, RedirectAttributes redirectAttributes) {
		bgCaseService.delete(bgCase);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/bg/bgCase/?repage";
	}

	@RequiresPermissions("bg:bgCase:selfEdit")
	@RequestMapping(value = {"self"})
	public String self(BgCase bgCase, HttpServletRequest request, HttpServletResponse response, Model model) {

		bgCase.setBusinessManId(UserUtils.getUser().getName());
		Page<BgCase> page = bgCaseService.findPage(new Page<BgCase>(request, response), bgCase);
		List<BgCase> list = page.getList();
		for (BgCase bgCase2 : list) {
			List<BgContacter> cs = bgCustomerService.findContacters(bgCase2.getCustomerId());
			if (CollectionUtils.isNotEmpty(cs)) {
				List<String> ls = (List<String>) CollectionUtils.collect(cs, new Transformer() {
					@Override
					public Object transform(Object input) {
						return ((BgContacter)input).getName();
					}
				});
				bgCase2.setContacters(org.apache.commons.lang3.StringUtils.join(ls));
				logger.info("contacters:{}",bgCase2.getContacters());
			}

		}
		model.addAttribute("page", page);
		return "modules/bg/bgCaseSelfList";
	}
	@RequiresPermissions("bg:bgCase:selfEdit")
	@RequestMapping(value = "selfSave")
	public String selfSave(BgCase bgCase, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bgCase)){
			return form(bgCase, model);
		}
		bgCaseService.save(bgCase);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/bg/bgCase/self?repage";
	}

}