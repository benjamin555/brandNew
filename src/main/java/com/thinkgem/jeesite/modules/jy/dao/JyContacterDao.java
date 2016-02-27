/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.jy.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.jy.entity.JyContacter;

/**
 * 客户进度DAO接口
 * @author sp
 * @version 2016-02-25
 */
@MyBatisDao
public interface JyContacterDao extends CrudDao<JyContacter> {
	
}