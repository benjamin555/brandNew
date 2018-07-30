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
import com.thinkgem.jeesite.modules.bg.dao.BgContacterDao;
import com.thinkgem.jeesite.modules.bg.dao.BgCustomerDao;
import com.thinkgem.jeesite.modules.bg.entity.BgContacter;
import com.thinkgem.jeesite.modules.bg.entity.BgCustomer;

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
		bgCustomer.setBgContacterList(findContacters(bgCustomer));
		return bgCustomer;
	}

	private List<BgContacter> findContacters(BgCustomer bgCustomer) {
		return bgContacterDao.findList(new BgContacter(bgCustomer));
	}
	
	public List<BgContacter> findContacters(String bgCustomerId) {
		BgCustomer bgCustomer = new BgCustomer(bgCustomerId);
		return bgContacterDao.findList(new BgContacter(bgCustomer));
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

    public Page<BgCustomer> findPageByBusinessMan(Page<BgCustomer> bgCustomerPage, BgCustomer bgCustomer) {
		bgCustomer.setPage(bgCustomerPage);
		bgCustomerPage.setList(dao.findListByBusinessMan(bgCustomer));
		return bgCustomerPage;
	}
}