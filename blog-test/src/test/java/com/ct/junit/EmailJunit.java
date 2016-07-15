package com.ct.junit;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ct.email.Email;
import com.ct.email.EmailSenderApi;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
public class EmailJunit {
	@Resource
	private EmailSenderApi emailSenderApi;
	@Test
	public void sendEmail(){
		Email email = new Email();
		email.setFrom("caishenchen@163.com");
		email.setTo("505035392@qq.com");
		email.setSubject("ouyeel");
		email.setHtml(true);
		StringBuffer sb = new StringBuffer();
		
		sb.append("<html>");
		sb.append("<head>");
		sb.append("    <meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\">");
		sb.append("    <title>Document</title>");
		sb.append("</head>");
		sb.append("<style>");
		sb.append("        *{margin:0px;padding:0px;}");
		sb.append("        body{font-family:\"dinprobold\",\"Microsoft YaHei\",\"微软雅黑\",Arial,Helvetica,sans-serif;color:gray;}");
		sb.append("        .big{margin: 15px 30px;overflow:hidden;}");
		sb.append("        .sma-k{margin:140px auto;height:500px;line-height:25px;}");
		sb.append("		p{margin:5px 0px 5px 0px;}");
		sb.append("    </style>");
		sb.append("<body>");
		sb.append(" <div class=\"big\">");
		sb.append("	<p>aaaaaaaaa!</p>");
		sb.append("	<p>aaaaaaa！</p>");
		sb.append("	<p>aaaaaaaaa</p>");
		sb.append("</div>");
		sb.append("");
		sb.append("</body>");
		sb.append("</html>");
		email.setText(sb.toString());
		emailSenderApi.sendEmail(email);
	}
}
