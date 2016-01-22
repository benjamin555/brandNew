/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bg.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 意向客户情况生成Entity
 * @author sp
 * @version 2016-01-22
 */
public class BgDailyCustomer extends DataEntity<BgDailyCustomer> {
	
	private static final long serialVersionUID = 1L;
	private String customerRemark;		// 意向客户情况
	private String hid;		// 日报id
	
	public BgDailyCustomer() {
		super();
	}

	public BgDailyCustomer(String id){
		super(id);
	}

	@Length(min=0, max=20, message="意向客户情况长度必须介于 0 和 20 之间")
	public String getCustomerRemark() {
		return customerRemark;
	}

	public void setCustomerRemark(String customerRemark) {
		this.customerRemark = customerRemark;
	}
	
	@Length(min=1, max=64, message="日报id长度必须介于 1 和 64 之间")
	public String getHid() {
		return hid;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}
	
}