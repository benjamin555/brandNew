package com.thinkgem.jeesite.modules.mail;

import java.util.Calendar;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @author 陈嘉镇
* @version 创建时间：2015-10-4 上午8:48:22
*/
public class MailFacade {
	
	private static final String MESSAGE_TYPE = "text/html;charset=gb2312";
	private   String systemMailPass;
	private   String systemMail;
	private String smtpHost;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public void sendMail(String to, String subject,
			String messageText) {
		try {
			this.sendMessage(smtpHost, systemMail, systemMailPass, to,
					subject, messageText, MESSAGE_TYPE);
		} catch (MessagingException e) {
			logger.error("",e);
		}
	}

	@SuppressWarnings("static-access")
	public  void sendMessage(String smtpHost, String from, String fromUserPassword, String to, String subject,
			String messageText, String messageType) throws MessagingException {
		// 第一步：配置javax.mail.Session对象
		logger.info("为" + smtpHost + "配置mail session对象");

		Properties props = new Properties();
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.starttls.enable", "true");//使用 STARTTLS安全连接
		props.put("mail.smtp.port", "465");			 //google使用465或587端口
		props.put("mail.smtp.auth", "true"); // 使用验证
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.debug", "false");
		Session mailSession = Session.getInstance(props, new MyAuthenticator(from, fromUserPassword));

		// 第二步：编写消息
		logger.info("编写消息from——to:" + from + "——" + to);

		InternetAddress fromAddress = new InternetAddress(from);
		InternetAddress toAddress = new InternetAddress(to);

		MimeMessage message = new MimeMessage(mailSession);

		message.setFrom(fromAddress);
		message.addRecipient(RecipientType.TO, toAddress);

		message.setSentDate(Calendar.getInstance().getTime());
		message.setSubject(subject);
		message.setContent(messageText, messageType);

		// 第三步：发送消息
		Transport transport = mailSession.getTransport("smtp");
		transport.connect(smtpHost, from, fromUserPassword);
		transport.send(message, message.getRecipients(RecipientType.TO));
		logger.info("message yes");
	}

	public String getSystemMailPass() {
		return systemMailPass;
	}

	public void setSystemMailPass(String systemMailPass) {
		this.systemMailPass = systemMailPass;
	}

	public String getSystemMail() {
		return systemMail;
	}

	public void setSystemMail(String systemMail) {
		this.systemMail = systemMail;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

}

class MyAuthenticator extends Authenticator {
	String userName = "";
	String password = "";

	public MyAuthenticator() {

	}

	public MyAuthenticator(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
