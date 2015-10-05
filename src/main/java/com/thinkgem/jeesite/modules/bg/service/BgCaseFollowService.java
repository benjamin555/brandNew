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
import com.thinkgem.jeesite.modules.bg.dao.BgCaseFollowDao;
import com.thinkgem.jeesite.modules.bg.entity.BgCaseFollow;
import com.thinkgem.jeesite.modules.mail.domain.MailVO;
import com.thinkgem.jeesite.modules.quartz.MailJob;
import com.thinkgem.jeesite.modules.quartz.SchedulerFacade;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 案例进度生成Service
 * @author sp
 * @version 2015-10-03
 */
@Service
@Transactional(readOnly = true)
public class BgCaseFollowService extends CrudService<BgCaseFollowDao, BgCaseFollow> {
	@Autowired
	private SchedulerFacade schedulerFacade;

	public BgCaseFollow get(String id) {
		return super.get(id);
	}
	
	public List<BgCaseFollow> findList(BgCaseFollow bgCaseFollow) {
		return super.findList(bgCaseFollow);
	}
	
	public Page<BgCaseFollow> findPage(Page<BgCaseFollow> page, BgCaseFollow bgCaseFollow) {
		return super.findPage(page, bgCaseFollow);
	}
	
	@Transactional(readOnly = false)
	public void save(BgCaseFollow bgCaseFollow) {
		super.save(bgCaseFollow);
		
		
		
		if(bgCaseFollow.getRemindTime()!=null){
			User u = UserUtils.getUser();
			String email = u.getEmail();
			if(StringUtils.isNotBlank(email)){
				//加入定时邮件
				MailVO vo  = new MailVO();
				vo.setSubject("案件提醒");
				vo.setContent(bgCaseFollow.getDescrip());
			
				vo.setToAddress(email);
				MailJob job = new MailJob(vo);
				
				schedulerFacade.addMailJob(job, bgCaseFollow.getRemindTime());
			}else {
				throw new RuntimeException("当前用户未设置邮箱，无法设置提醒");
			}
		}
		
	}
	
	@Transactional(readOnly = false)
	public void delete(BgCaseFollow bgCaseFollow) {
		super.delete(bgCaseFollow);
	}
	
}