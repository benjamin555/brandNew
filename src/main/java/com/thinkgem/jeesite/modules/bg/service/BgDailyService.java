/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bg.dao.BgDailyDao;
import com.thinkgem.jeesite.modules.bg.entity.BgDaily;

/**
 * 日报生成Service
 * @author sp
 * @version 2015-12-22
 */
@Service
@Transactional(readOnly = true)
public class BgDailyService extends CrudService<BgDailyDao, BgDaily> {

	public BgDaily get(String id) {
		return super.get(id);
	}
	
	public List<BgDaily> findList(BgDaily bgDaily) {
		return super.findList(bgDaily);
	}
	
	public Page<BgDaily> findPage(Page<BgDaily> page, BgDaily bgDaily) {
		return super.findPage(page, bgDaily);
	}
	
	@Transactional(readOnly = false)
	public void save(BgDaily bgDaily) {
		super.save(bgDaily);
	}
	
	@Transactional(readOnly = false)
	public void delete(BgDaily bgDaily) {
		super.delete(bgDaily);
	}
	
}