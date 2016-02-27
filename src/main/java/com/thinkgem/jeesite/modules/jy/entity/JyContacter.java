/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.jy.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 客户进度Entity
 * @author sp
 * @version 2016-02-25
 */
public class JyContacter extends DataEntity<JyContacter> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String phoneNumber;		// 电话号码
	private JyCustomer hid;		// hid 父类
	private String qqowx;		// QQ或微信
	
	public JyContacter() {
		super();
	}

	public JyContacter(String id){
		super(id);
	}

	public JyContacter(JyCustomer hid){
		this.hid = hid;
	}

	@Length(min=0, max=64, message="名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=20, message="电话号码长度必须介于 0 和 20 之间")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Length(min=0, max=64, message="hid长度必须介于 0 和 64 之间")
	public JyCustomer getHid() {
		return hid;
	}

	public void setHid(JyCustomer hid) {
		this.hid = hid;
	}
	
	@Length(min=0, max=32, message="QQ或微信长度必须介于 0 和 32 之间")
	public String getQqowx() {
		return qqowx;
	}

	public void setQqowx(String qqowx) {
		this.qqowx = qqowx;
	}
	
}