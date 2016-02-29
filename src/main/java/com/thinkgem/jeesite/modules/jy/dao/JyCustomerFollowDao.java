/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.jy.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.jy.entity.JyCustomerFollow;

/**
 * 京远客户跟进DAO接口
 * @author sp
 * @version 2016-02-28
 */
@MyBatisDao
public interface JyCustomerFollowDao extends CrudDao<JyCustomerFollow> {
	
}