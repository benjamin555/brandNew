/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bg.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 快件生成Entity
 * @author sp
 * @version 2015-06-06
 */
public class BgExpress extends DataEntity<BgExpress> {
	
	private static final long serialVersionUID = 1L;
	private Date diliveryDate;		// 发件时间
	private String file;		// 文件
	private String address;		// 地址
	private String phoneNumber;		// 电话
	private String fee;		// 费用
	private String status;		// 情况
	private Date keeponDate;		// 业务根据日
	private String keeponStatus;		// 跟进情况
	
	public BgExpress() {
		super();
	}

	public BgExpress(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDiliveryDate() {
		return diliveryDate;
	}

	public void setDiliveryDate(Date diliveryDate) {
		this.diliveryDate = diliveryDate;
	}
	
	@Length(min=0, max=64, message="文件长度必须介于 0 和 64 之间")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	@Length(min=0, max=400, message="地址长度必须介于 0 和 400 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=32, message="电话长度必须介于 0 和 32 之间")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}
	
	@Length(min=0, max=64, message="情况长度必须介于 0 和 64 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getKeeponDate() {
		return keeponDate;
	}

	public void setKeeponDate(Date keeponDate) {
		this.keeponDate = keeponDate;
	}
	
	@Length(min=0, max=400, message="跟进情况长度必须介于 0 和 400 之间")
	public String getKeeponStatus() {
		return keeponStatus;
	}

	public void setKeeponStatus(String keeponStatus) {
		this.keeponStatus = keeponStatus;
	}
	
}