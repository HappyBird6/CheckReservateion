

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SMTP {
	private String fromEmail = "email";
	private String fromPassword = "password";
	
	public void sendMail(String toEmail, String toName, String subject, String content) {
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");

		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		MyAuthenticator authenticator = new MyAuthenticator(fromEmail, fromPassword);
		
		Session session = Session.getDefaultInstance(props, authenticator);
		
		// 메세지 설정
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setHeader("Content-type","text/html; charset=utf-8");
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail,toName,"utf-8"));
			msg.setSubject(subject);
			msg.setContent(content,"text/html; charset=utf-8");
			
			msg.setSentDate(new java.util.Date());
			
			Transport.send(msg);
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}
	}
}
