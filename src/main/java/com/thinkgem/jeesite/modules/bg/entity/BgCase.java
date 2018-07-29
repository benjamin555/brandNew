/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bg.entity;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 案例生成Entity
 * @author sp
 * @version 2015-05-31
 */
public class BgCase extends DataEntity<BgCase> {
	
	private static final long serialVersionUID = 1L;
	private Date lianDate;		// 立案日期
	private String itemName;		// 专利/商标名称
	private String regNo;		// 注册号
	private String delegateItem;		// 代理事项
	private Double fixFee;		// 规费
	private Double agentFee;		// 代理费
	private Double receiveFee;		// 已收金额
	private String phoneManId;		// 话务员,修改成欠费栏
	private String businessManId;		// 业务员的名字
	private String customerId;		// 客户
	private Integer feedbackMonth;		// 提成月份
	private String contacterId;		// 联系人
	private Date acceptDate;		// 国家受通时间
	private Date rejectDate;		// 驳回时间
	private Date certificateDate;		// 拿证时间
	private List<BgCaseFollow> bgCaseFollowList = Lists.newArrayList();		// 拿证时间


	public List<BgCaseFollow> getBgCaseFollowList() {
		return bgCaseFollowList;
	}

	public void setBgCaseFollowList(List<BgCaseFollow> bgCaseFollowList) {
		this.bgCaseFollowList = bgCaseFollowList;
	}

	/**
	 * 联系人名称字符串拼接
	 */
	private String contacters;
	
	private BgCustomer customer;
	
	private BgContacter contacter;
	
	public BgCase() {
		super();
	}

	public BgCase(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLianDate() {
		return lianDate;
	}

	public void setLianDate(Date lianDate) {
		this.lianDate = lianDate;
	}
	
	@Length(min=0, max=60, message="专利/商标名称长度必须介于 0 和 60 之间")
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@Length(min=0, max=20, message="注册号长度必须介于 0 和 20 之间")
	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	
	@Length(min=0, max=20, message="代理事项长度必须介于 0 和 20 之间")
	public String getDelegateItem() {
		return delegateItem;
	}

	public void setDelegateItem(String delegateItem) {
		this.delegateItem = delegateItem;
	}
	
	public Double getFixFee() {
		return fixFee;
	}

	public void setFixFee(Double fixFee) {
		this.fixFee = fixFee;
	}
	
	public Double getAgentFee() {
		return agentFee;
	}

	public void setAgentFee(Double agentFee) {
		this.agentFee = agentFee;
	}
	
	public Double getReceiveFee() {
		return receiveFee;
	}

	public void setReceiveFee(Double receiveFee) {
		this.receiveFee = receiveFee;
	}
	
	@Length(min=0, max=64, message="话务员长度必须介于 0 和 64 之间")
	public String getPhoneManId() {
		return phoneManId;
	}

	public void setPhoneManId(String phoneManId) {
		this.phoneManId = phoneManId;
	}
	
	@Length(min=0, max=64, message="业务员长度必须介于 0 和 64 之间")
	public String getBusinessManId() {
		return businessManId;
	}

	public void setBusinessManId(String businessManId) {
		this.businessManId = businessManId;
	}
	
	@Length(min=0, max=64, message="客户长度必须介于 0 和 64 之间")
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public Integer getFeedbackMonth() {
		return feedbackMonth;
	}

	public void setFeedbackMonth(Integer feedbackMonth) {
		this.feedbackMonth = feedbackMonth;
	}
	
	@Length(min=0, max=64, message="联系人长度必须介于 0 和 64 之间")
	public String getContacterId() {
		return contacterId;
	}

	public void setContacterId(String contacterId) {
		this.contacterId = contacterId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRejectDate() {
		return rejectDate;
	}

	public void setRejectDate(Date rejectDate) {
		this.rejectDate = rejectDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCertificateDate() {
		return certificateDate;
	}

	public void setCertificateDate(Date certificateDate) {
		this.certificateDate = certificateDate;
	}

	public BgCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(BgCustomer customer) {
		this.customer = customer;
	}

	public String getContacters() {
		return contacters;
	}

	public void setContacters(String contacters) {
		this.contacters = contacters;
	}

	public BgContacter getContacter() {
		return contacter;
	}

	public void setContacter(BgContacter contacter) {
		this.contacter = contacter;
	}
	
	
}