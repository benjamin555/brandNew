/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bg.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 日报生成Entity
 * @author sp
 * @version 2015-12-22
 */
public class BgDaily extends DataEntity<BgDaily> {
	
	private static final long serialVersionUID = 1L;
	private Date riDate;		// 日期
	private String todayRemark;		// 意向客户及情况
	private String tomorrowPlan;		// 明天重点客户及情况
	
	private String agentJob;//代理事项
	
	private List<BgDailyContacter> bgDailyContacterList;
	
	private List<BgDailyCustomer> bgDailyCustomerList;
	
	public BgDaily() {
		super();
	}

	public BgDaily(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRiDate() {
		return riDate;
	}

	public void setRiDate(Date riDate) {
		this.riDate = riDate;
	}
	
	@Length(min=0, max=1000, message="意向客户及情况长度必须介于 0 和 1000 之间")
	public String getTodayRemark() {
		return todayRemark;
	}

	public void setTodayRemark(String todayRemark) {
		this.todayRemark = todayRemark;
	}
	
	@Length(min=0, max=1000, message="明天重点客户及情况长度必须介于 0 和 1000 之间")
	public String getTomorrowPlan() {
		return tomorrowPlan;
	}

	public void setTomorrowPlan(String tomorrowPlan) {
		this.tomorrowPlan = tomorrowPlan;
	}

	public List<BgDailyContacter> getBgDailyContacterList() {
		return bgDailyContacterList;
	}

	public void setBgDailyContacterList(List<BgDailyContacter> bgDailyContacterList) {
		this.bgDailyContacterList = bgDailyContacterList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAgentJob() {
		return agentJob;
	}

	public void setAgentJob(String agentJob) {
		this.agentJob = agentJob;
	}

	public List<BgDailyCustomer> getBgDailyCustomerList() {
		return bgDailyCustomerList;
	}

	public void setBgDailyCustomerList(List<BgDailyCustomer> bgDailyCustomerList) {
		this.bgDailyCustomerList = bgDailyCustomerList;
	}
	
	
	
}