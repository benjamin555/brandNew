/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bg.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 案例进度生成Entity
 * @author sp
 * @version 2015-10-05
 */
public class BgCaseFollow extends DataEntity<BgCaseFollow> {
	
	private static final long serialVersionUID = 1L;
	private String descrip;		// descrip : 描述
	private String caseId;		// 案件id
	private Date remindTime;		// 提醒时间
	
	public BgCaseFollow() {
		super();
	}

	public BgCaseFollow(String id){
		super(id);
	}

	@Length(min=0, max=4000, message="descrip : 描述长度必须介于 0 和 4000 之间")
	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	
	@Length(min=1, max=64, message="案件id长度必须介于 1 和 64 之间")
	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRemindTime() {
		return remindTime;
	}

	public void setRemindTime(Date remindTime) {
		this.remindTime = remindTime;
	}

	@Override
	public String toString() {
		return descrip;
	}
}