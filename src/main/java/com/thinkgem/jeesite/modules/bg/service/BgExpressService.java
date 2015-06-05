/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bg.entity.BgExpress;
import com.thinkgem.jeesite.modules.bg.dao.BgExpressDao;

/**
 * 快件生成Service
 * @author sp
 * @version 2015-06-06
 */
@Service
@Transactional(readOnly = true)
public class BgExpressService extends CrudService<BgExpressDao, BgExpress> {

	public BgExpress get(String id) {
		return super.get(id);
	}
	
	public List<BgExpress> findList(BgExpress bgExpress) {
		return super.findList(bgExpress);
	}
	
	public Page<BgExpress> findPage(Page<BgExpress> page, BgExpress bgExpress) {
		return super.findPage(page, bgExpress);
	}
	
	@Transactional(readOnly = false)
	public void save(BgExpress bgExpress) {
		super.save(bgExpress);
	}
	
	@Transactional(readOnly = false)
	public void delete(BgExpress bgExpress) {
		super.delete(bgExpress);
	}
	
}