/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bg.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 案例进度生成Entity
 * @author sp
 * @version 2015-10-03
 */
public class BgCaseFollow extends DataEntity<BgCaseFollow> {
	
	private static final long serialVersionUID = 1L;
	private String descrip;		// 描述
	private String caseId;		// 案件id
	
	public BgCaseFollow() {
		super();
	}

	public BgCaseFollow(String id){
		super(id);
	}

	@Length(min=0, max=4000, message="描述长度必须介于 0 和 4000 之间")
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
	
}