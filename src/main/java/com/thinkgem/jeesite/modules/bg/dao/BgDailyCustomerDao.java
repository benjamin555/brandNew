/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bg.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.bg.entity.BgDailyCustomer;

/**
 * 意向客户情况生成DAO接口
 * @author sp
 * @version 2016-01-22
 */
@MyBatisDao
public interface BgDailyCustomerDao extends CrudDao<BgDailyCustomer> {
	
}