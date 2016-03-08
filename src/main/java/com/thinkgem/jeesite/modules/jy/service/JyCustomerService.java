/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.jy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.jy.dao.JyContacterDao;
import com.thinkgem.jeesite.modules.jy.dao.JyCustomerDao;
import com.thinkgem.jeesite.modules.jy.dao.JyCustomerFollowDao;
import com.thinkgem.jeesite.modules.jy.entity.JyContacter;
import com.thinkgem.jeesite.modules.jy.entity.JyCustomer;
import com.thinkgem.jeesite.modules.jy.entity.JyCustomerFollow;

/**
 * 客户进度Service
 * @author sp
 * @version 2016-02-25
 */
@Service
@Transactional(readOnly = true)
public class JyCustomerService extends CrudService<JyCustomerDao, JyCustomer> {

	@Autowired
	private JyContacterDao jyContacterDao;
	@Autowired
	private JyCustomerFollowDao jyCustomerFollowDao;
	
	public JyCustomer get(String id) {
		JyCustomer jyCustomer = super.get(id);
		jyCustomer.setJyContacterList(jyContacterDao.findList(new JyContacter(jyCustomer)));
		jyCustomer.setJyCustomerFollowList(jyCustomerFollowDao.findList(new JyCustomerFollow(jyCustomer)));
		return jyCustomer;
	}
	
	public List<JyCustomer> findList(JyCustomer jyCustomer) {
		return super.findList(jyCustomer);
	}
	
	public Page<JyCustomer> findPage(Page<JyCustomer> page, JyCustomer jyCustomer) {
		Page<JyCustomer> p = super.findPage(page, jyCustomer);
		List<JyCustomer> list = p.getList();
		for (JyCustomer jyCustomer2 : list) {
			jyCustomer2.setJyContacterList(jyContacterDao.findList(new JyContacter(jyCustomer2)));
			jyCustomer2.setJyCustomerFollowList(jyCustomerFollowDao.findList(new JyCustomerFollow(jyCustomer2)));

		}
		return p;
	}
	
	@Transactional(readOnly = false)
	public void save(JyCustomer jyCustomer) {
		super.save(jyCustomer);
		for (JyContacter jyContacter : jyCustomer.getJyContacterList()){
			if (jyContacter.getId() == null){
				continue;
			}
			if (JyContacter.DEL_FLAG_NORMAL.equals(jyContacter.getDelFlag())){
				if (StringUtils.isBlank(jyContacter.getId())){
					jyContacter.setHid(jyCustomer);
					jyContacter.preInsert();
					jyContacterDao.insert(jyContacter);
				}else{
					jyContacter.preUpdate();
					jyContacterDao.update(jyContacter);
				}
			}else{
				jyContacterDao.delete(jyContacter);
			}
		}
		for (JyCustomerFollow jyCustomerFollow : jyCustomer.getJyCustomerFollowList()){
			if (jyCustomerFollow.getId() == null){
				continue;
			}
			if (JyCustomerFollow.DEL_FLAG_NORMAL.equals(jyCustomerFollow.getDelFlag())){
				if (StringUtils.isBlank(jyCustomerFollow.getId())){
					jyCustomerFollow.setHid(jyCustomer);
					jyCustomerFollow.preInsert();
					jyCustomerFollowDao.insert(jyCustomerFollow);
				}else{
					jyCustomerFollow.preUpdate();
					jyCustomerFollowDao.update(jyCustomerFollow);
				}
			}else{
				jyCustomerFollowDao.delete(jyCustomerFollow);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(JyCustomer jyCustomer) {
		super.delete(jyCustomer);
		jyContacterDao.delete(new JyContacter(jyCustomer));
		jyCustomerFollowDao.delete(new JyCustomerFollow(jyCustomer));
	}
	
}