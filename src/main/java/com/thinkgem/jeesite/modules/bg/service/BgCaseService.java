/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bg.service;

import java.util.List;

import com.thinkgem.jeesite.modules.bg.dao.BgCaseFollowDao;
import com.thinkgem.jeesite.modules.bg.entity.BgCaseFollow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bg.entity.BgCase;
import com.thinkgem.jeesite.modules.bg.dao.BgCaseDao;

/**
 * 单表生成Service
 * @author sp
 * @version 2015-05-30
 */
@Service
@Transactional(readOnly = true)
public class BgCaseService extends CrudService<BgCaseDao, BgCase> {
	@Autowired
	private BgCaseFollowDao bgCaseFollowDao;

	public BgCase get(String id) {
		BgCase bg = super.get(id);
		//加载跟进记录
		BgCaseFollow bcf = new BgCaseFollow();
		bcf.setCaseId(bg.getId());
		List<BgCaseFollow> bs = bgCaseFollowDao.findList(bcf);
		bg.setBgCaseFollowList(bs);
		return bg;

	}
	
	public List<BgCase> findList(BgCase bgCase) {
		return super.findList(bgCase);
	}
	
	public Page<BgCase> findPage(Page<BgCase> page, BgCase bgCase) {
		return super.findPage(page, bgCase);
	}
	
	@Transactional(readOnly = false)
	public void save(BgCase bgCase) {
		super.save(bgCase);
	}
	
	@Transactional(readOnly = false)
	public void delete(BgCase bgCase) {
		super.delete(bgCase);
	}
	
}