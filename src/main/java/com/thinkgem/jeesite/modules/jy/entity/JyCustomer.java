/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.jy.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 客户进度Entity
 * @author sp
 * @version 2016-02-25
 */
public class JyCustomer extends DataEntity<JyCustomer> {
	
	private static final long serialVersionUID = 1L;
	private String typ;		// 客户类型
	private String agent;		// 代理项目
	private String level;		// 客户级别
	private User currentFollower;		// 当前跟进人
	private List<JyContacter> jyContacterList = Lists.newArrayList();		// 子表列表
	private List<JyCustomerFollow> jyCustomerFollowList = Lists.newArrayList();		// 子表列表
	
//	//联系人描述
//	private String contacters;
//	//最后跟进内容
//	private String lastFollwerDesc;
	
	public JyCustomer() {
		super();
	}

	public JyCustomer(String id){
		super(id);
	}

	@Length(min=1, max=32, message="客户类型长度必须介于 1 和 32 之间")
	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}
	
	@Length(min=0, max=32, message="代理项目长度必须介于 0 和 32 之间")
	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}
	
	@Length(min=0, max=32, message="客户级别长度必须介于 0 和 32 之间")
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	
	public User getCurrentFollower() {
		return currentFollower;
	}

	public void setCurrentFollower(User currentFollower) {
		this.currentFollower = currentFollower;
	}

	public List<JyContacter> getJyContacterList() {
		return jyContacterList;
	}

	public void setJyContacterList(List<JyContacter> jyContacterList) {
		this.jyContacterList = jyContacterList;
	}
	public List<JyCustomerFollow> getJyCustomerFollowList() {
		return jyCustomerFollowList;
	}

	public void setJyCustomerFollowList(List<JyCustomerFollow> jyCustomerFollowList) {
		this.jyCustomerFollowList = jyCustomerFollowList;
	}

	/**
	 * 联系人描述
	 * @return
	 */
	public String getContacters() {
		List<String> cList =  new ArrayList<String>();
		if (CollectionUtils.isNotEmpty(getJyContacterList())) {
			List<JyContacter> jyContacterList2 = getJyContacterList();
			for (JyContacter jyContacter : jyContacterList2) {
				cList.add(jyContacter.getName());
			}
		}
		return StringUtils.join(cList);
	}

	/**
	 * 最后根据内容
	 * @param contacters
	 * @return
	 */
	public String getLastFollowDesc() {
		
		if (CollectionUtils.isNotEmpty(jyCustomerFollowList)) {
			return jyCustomerFollowList.get(0).getFollowContent();
		}
		
		return null;
	}

	
	
}