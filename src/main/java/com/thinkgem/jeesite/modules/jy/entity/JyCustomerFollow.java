/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.jy.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 京远客户跟进Entity
 * @author sp
 * @version 2016-02-28
 */
public class JyCustomerFollow extends DataEntity<JyCustomerFollow> {
	
	private static final long serialVersionUID = 1L;
	private Date dat;		// 客户类型
	private String followContent;		// 跟进内容
	private User follower;		// 跟进人
	private JyCustomer hid;		// hid 父类
	
	public JyCustomerFollow() {
		super();
	}

	public JyCustomerFollow(String id){
		super(id);
	}

	public JyCustomerFollow(JyCustomer hid){
		this.hid = hid;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getDat() {
		return dat;
	}

	public void setDat(Date dat) {
		this.dat = dat;
	}

	@Length(min=0, max=400, message="跟进内容长度必须介于 0 和 400 之间")
	public String getFollowContent() {
		return followContent;
	}

	public void setFollowContent(String followContent) {
		this.followContent = followContent;
	}
	
	
	public User getFollower() {
		return follower;
	}

	public void setFollower(User follower) {
		this.follower = follower;
	}
	
	@Length(min=0, max=64, message="hid长度必须介于 0 和 64 之间")
	public JyCustomer getHid() {
		return hid;
	}

	public void setHid(JyCustomer hid) {
		this.hid = hid;
	}
	
}