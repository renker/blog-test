package com.ct.email;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.geronimo.mail.util.Base64;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailSender implements EmailSenderApi{
	private JavaMailSender javaMailSender;
	
	public void sendEmail(Email email){
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
			helper.setFrom(email.getFrom());
			helper.setTo(email.getTo());
			helper.setSubject(email.getSubject());
			helper.setText(email.getText(), email.isHtml());
			javaMailSender.send(mimeMessage);
		} catch(MessagingException e){
			e.printStackTrace();
		} catch (MailException e) {
			e.printStackTrace();
		}
		
	}

	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
}
