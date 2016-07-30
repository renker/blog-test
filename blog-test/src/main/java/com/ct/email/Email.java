package com.ct.email;

import java.io.Serializable;

public class Email implements Serializable{
	private static final long serialVersionUID = 1L;
	/** 发件人邮箱 */
	private String from;
	/** 主题 */
	private String subject;
	/** 收件人邮箱 */
	private String to;
	/** 邮件正文 */
	private String text;
	/** 是否是html */
	private boolean html = false; 
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isHtml() {
		return html;
	}
	public void setHtml(boolean html) {
		this.html = html;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	
}
