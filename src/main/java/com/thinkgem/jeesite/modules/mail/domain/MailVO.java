package com.thinkgem.jeesite.modules.mail.domain;

import java.io.Serializable;

/**
* @author 陈嘉镇
* @version 创建时间：2015-10-5 上午9:55:05
*/
public class MailVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4976937783406061246L;

	private String subject;
	
	private String content;
	
	private String toAddress;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	@Override
	public String toString() {
		return "MailVO [subject=" + subject + ", content=" + content + ", toAddress=" + toAddress + "]";
	}
	
	

}
