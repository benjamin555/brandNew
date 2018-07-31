/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bg.service;

import java.util.List;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.bg.dao.BgCaseFollowDao;
import com.thinkgem.jeesite.modules.bg.entity.BgCaseFollow;
import org.apache.commons.collections.CollectionUtils;
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

	/**
	 * 迁移案件的业务员，并更新跟进
	 * @param oldBusinessMan
	 * @param newBusinessMan
	 */
	@Transactional(readOnly = false)
	public void transfer(String oldBusinessMan, String newBusinessMan) {
		if(StringUtils.isEmpty(oldBusinessMan)){
			throw new RuntimeException("原业务员不能为空.");
		}
		if(StringUtils.isEmpty(newBusinessMan)){
			throw new RuntimeException("新业务员不能为空.");
		}

		BgCase search = new BgCase();
		search.setBusinessManId(oldBusinessMan);
		List<BgCase> l = dao.findList(search);
		if(CollectionUtils.isEmpty(l)){
			throw new RuntimeException(oldBusinessMan+"没有案件");
		}else{
			for (BgCase bgCase:
				 l) {
				bgCase.setBusinessManId(newBusinessMan);
				save(bgCase);
				BgCaseFollow bcf = new BgCaseFollow();
				bcf.preInsert();
				bcf.setCaseId(bgCase.getId());
				String d = String.format("业务员调整：%s -> %s.",oldBusinessMan,newBusinessMan);
				bcf.setDescrip(d);
				bgCaseFollowDao.insert(bcf);
			}
		}

	}
}