/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bg.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.bg.entity.BgDailyContacter;

/**
 * 日报生成DAO接口
 * @author sp
 * @version 2015-12-31
 */
@MyBatisDao
public interface BgDailyContacterDao extends CrudDao<BgDailyContacter> {
	
}