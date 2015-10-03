/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bg.entity.BgCaseFollow;
import com.thinkgem.jeesite.modules.bg.dao.BgCaseFollowDao;

/**
 * 案例进度生成Service
 * @author sp
 * @version 2015-10-03
 */
@Service
@Transactional(readOnly = true)
public class BgCaseFollowService extends CrudService<BgCaseFollowDao, BgCaseFollow> {

	public BgCaseFollow get(String id) {
		return super.get(id);
	}
	
	public List<BgCaseFollow> findList(BgCaseFollow bgCaseFollow) {
		return super.findList(bgCaseFollow);
	}
	
	public Page<BgCaseFollow> findPage(Page<BgCaseFollow> page, BgCaseFollow bgCaseFollow) {
		return super.findPage(page, bgCaseFollow);
	}
	
	@Transactional(readOnly = false)
	public void save(BgCaseFollow bgCaseFollow) {
		super.save(bgCaseFollow);
	}
	
	@Transactional(readOnly = false)
	public void delete(BgCaseFollow bgCaseFollow) {
		super.delete(bgCaseFollow);
	}
	
}