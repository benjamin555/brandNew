/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bg.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 日报生成Entity
 * @author sp
 * @version 2015-12-31
 */
public class BgDailyContacter extends DataEntity<BgDailyContacter> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称 : 名称
	private String mobileNumber;		// 手机号码 : 手机号码
	private String brand;		// 商标
	private String typ;		// 类别
	private String hid;		// 日报id
	
	public BgDailyContacter() {
		super();
	}

	public BgDailyContacter(String id){
		super(id);
	}

	@Length(min=0, max=64, message="名称 : 名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=20, message="手机号码 : 手机号码长度必须介于 0 和 20 之间")
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	@Length(min=0, max=20, message="商标长度必须介于 0 和 20 之间")
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	@Length(min=0, max=20, message="类别长度必须介于 0 和 20 之间")
	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}
	
	@Length(min=1, max=64, message="日报id长度必须介于 1 和 64 之间")
	public String getHid() {
		return hid;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}
	
}