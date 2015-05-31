/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bg.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 主子表生成Entity
 * @author sp
 * @version 2015-05-30
 */
public class BgContacter extends DataEntity<BgContacter> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String mobileNumber;		// 手机号码
	private String fixedPhoneNumber;		// 固定电话号码
	private String faxNumber;		// 传真号码
	private BgCustomer customer;		// 客户id 父类
	
	public BgContacter() {
		super();
	}

	public BgContacter(String id){
		super(id);
	}

	public BgContacter(BgCustomer customer){
		this.customer = customer;
	}

	@Length(min=0, max=64, message="名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=20, message="手机号码长度必须介于 0 和 20 之间")
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	@Length(min=0, max=20, message="固定电话号码长度必须介于 0 和 20 之间")
	public String getFixedPhoneNumber() {
		return fixedPhoneNumber;
	}

	public void setFixedPhoneNumber(String fixedPhoneNumber) {
		this.fixedPhoneNumber = fixedPhoneNumber;
	}
	
	@Length(min=0, max=20, message="传真号码长度必须介于 0 和 20 之间")
	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	
	@Length(min=1, max=64, message="客户id长度必须介于 1 和 64 之间")
	public BgCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(BgCustomer customer) {
		this.customer = customer;
	}
	
}