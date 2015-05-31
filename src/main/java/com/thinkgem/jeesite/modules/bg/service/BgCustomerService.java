/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.bg.entity.BgCustomer;
import com.thinkgem.jeesite.modules.bg.dao.BgCustomerDao;
import com.thinkgem.jeesite.modules.bg.entity.BgContacter;
import com.thinkgem.jeesite.modules.bg.dao.BgContacterDao;

/**
 * 主子表生成Service
 * @author sp
 * @version 2015-05-30
 */
@Service
@Transactional(readOnly = true)
public class BgCustomerService extends CrudService<BgCustomerDao, BgCustomer> {

	@Autowired
	private BgContacterDao bgContacterDao;
	
	public BgCustomer get(String id) {
		BgCustomer bgCustomer = super.get(id);
		bgCustomer.setBgContacterList(bgContacterDao.findList(new BgContacter(bgCustomer)));
		return bgCustomer;
	}
	
	public List<BgCustomer> findList(BgCustomer bgCustomer) {
		return super.findList(bgCustomer);
	}
	
	public Page<BgCustomer> findPage(Page<BgCustomer> page, BgCustomer bgCustomer) {
		return super.findPage(page, bgCustomer);
	}
	
	@Transactional(readOnly = false)
	public void save(BgCustomer bgCustomer) {
		super.save(bgCustomer);
		for (BgContacter bgContacter : bgCustomer.getBgContacterList()){
			if (bgContacter.getId() == null){
				continue;
			}
			if (BgContacter.DEL_FLAG_NORMAL.equals(bgContacter.getDelFlag())){
				if (StringUtils.isBlank(bgContacter.getId())){
					bgContacter.setCustomer(bgCustomer);
					bgContacter.preInsert();
					bgContacterDao.insert(bgContacter);
				}else{
					bgContacter.preUpdate();
					bgContacterDao.update(bgContacter);
				}
			}else{
				bgContacterDao.delete(bgContacter);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(BgCustomer bgCustomer) {
		super.delete(bgCustomer);
		bgContacterDao.delete(new BgContacter(bgCustomer));
	}
	
}