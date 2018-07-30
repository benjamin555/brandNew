/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bg.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 主子表生成Entity
 * @author sp
 * @version 2015-05-30
 */
public class BgCustomer extends DataEntity<BgCustomer> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private List<BgContacter> bgContacterList = Lists.newArrayList();		// 子表列表

	private String businessMan; // 业务员
	
	public BgCustomer() {
		super();
	}

	public BgCustomer(String id){
		super(id);
	}

	@Length(min=1, max=64, message="名称长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<BgContacter> getBgContacterList() {
		return bgContacterList;
	}

	public void setBgContacterList(List<BgContacter> bgContacterList) {
		this.bgContacterList = bgContacterList;
	}

	public String getBusinessMan() {
		return businessMan;
	}

	public void setBusinessMan(String businessMan) {
		this.businessMan = businessMan;
	}
}