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
import com.thinkgem.jeesite.modules.bg.dao.BgDailyContacterDao;
import com.thinkgem.jeesite.modules.bg.dao.BgDailyDao;
import com.thinkgem.jeesite.modules.bg.entity.BgDaily;
import com.thinkgem.jeesite.modules.bg.entity.BgDailyContacter;

/**
 * 日报生成Service
 * @author sp
 * @version 2015-12-22
 */
@Service
@Transactional(readOnly = true)
public class BgDailyService extends CrudService<BgDailyDao, BgDaily> {
	@Autowired
	private BgDailyContacterDao bgDailyContacterDao;

	public BgDaily get(String id) {
		BgDaily bgDaily = super.get(id);
		bgDaily.setBgDailyContacterList(findContacters(bgDaily));
		return bgDaily;
	}

	private List<BgDailyContacter> findContacters(BgDaily bgDaily) {
		BgDailyContacter bgDailyContacter =new BgDailyContacter();
		bgDailyContacter.setHid(bgDaily.getId());
		return bgDailyContacterDao.findList(bgDailyContacter);
	}

	public List<BgDaily> findList(BgDaily bgDaily) {
		return super.findList(bgDaily);
	}

	public Page<BgDaily> findPage(Page<BgDaily> page, BgDaily bgDaily) {
		return super.findPage(page, bgDaily);
	}

	@Transactional(readOnly = false)
	public void save(BgDaily bgDaily) {
		super.save(bgDaily);
		for (BgDailyContacter bgContacter : bgDaily.getBgDailyContacterList()) {
			if (bgDaily.getId() == null) {
				break;
			}
			bgContacter.setHid(bgDaily.getId());
			if (BgDailyContacter.DEL_FLAG_NORMAL.equals(bgContacter.getDelFlag())) {
				if (StringUtils.isBlank(bgContacter.getId())) {
					bgContacter.preInsert();
					bgDailyContacterDao.insert(bgContacter);
				} else {
					bgContacter.preUpdate();
					bgDailyContacterDao.update(bgContacter);
				}
			} else {
				bgDailyContacterDao.delete(bgContacter);
			}
		}

	}

	@Transactional(readOnly = false)
	public void delete(BgDaily bgDaily) {
		super.delete(bgDaily);
	}

}