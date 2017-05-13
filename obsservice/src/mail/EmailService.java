package mail;


import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class EmailService
{
	private String emailTo;
	private String emailFrom;
	private String host;
	private Properties properties;
	private Session session;
	private final String username = "wplmithun@gmail.com";
	private final String password = "123mithun";
	
	public void setEmailTo(String to)
	{
		this.emailTo=to;
	}
	
	public void setEmailFrom(String from)
	{
		this.emailFrom=from;
	}
	
	public void setHost(String host)
	{
		this.host=host;
	}
	
	public void setProperties()
	{		
		MailSSLSocketFactory socketFactory;
		try {
			socketFactory = new MailSSLSocketFactory();
			socketFactory.setTrustAllHosts(true);
			properties = new Properties();
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", host);
			properties.put("mail.smtp.port", "587");
			properties.put("mail.imap.ssl.socketFactory", socketFactory);
			properties.put("mail.protocol.ssl.trust", "smtp.gmail.com");
			
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
	
	public void setSession()
	{
		session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username,password);
					}
				});
	}
	
	public String getEmailTo()
	{
		return emailTo;
	}
	
	public String getEmailFrom()
	{
		return emailFrom;
	}
	
	public String getHost()
	{
		return host;
	}
	
	public Properties getObjProperties()
	{
		return properties;
	}
	
	public Session getCurrentSession()
	{
		return session;
	}
	
	public void sendEmail(String subject, String msg)
	{		
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailFrom));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(emailTo));
			message.setSubject(subject);
			message.setText(msg);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}

}







//package mail;
//
//
//import java.security.GeneralSecurityException;
//import java.util.Properties;
//
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//import com.sun.mail.util.MailSSLSocketFactory;
//
//public class EmailService
//{
//	private String emailTo;
//	private String emailFrom;
//	private String host;
//	private Properties properties;
//	private Session session;
//	private final String username = "wplmithun@gmail.com";
//	private final String password = "123mithun";
//	
//	public void setEmailTo(String to)
//	{
//		this.emailTo=to;
//	}
//	
//	public void setEmailFrom(String from)
//	{
//		this.emailFrom=from;
//	}
//	
//	public void setHost(String host)
//	{
//		this.host=host;
//	}
//	
//	public void setProperties()
//	{		
//		MailSSLSocketFactory socketFactory;
//		try {
//			socketFactory = new MailSSLSocketFactory();
//			socketFactory.setTrustAllHosts(true);
//			properties = new Properties();
//			properties.put("mail.smtp.auth", "true");
////			properties.put("mail.smtp.starttls.enable", "true");
//			properties.put("mail.smtp.host", host);
//			properties.put("mail.smtp.port", "587");
//			properties.put("mail.imap.ssl.socketFactory", socketFactory);
//			properties.put("mail.protocol.ssl.trust", "smtp.gmail.com");
//			
//		} catch (GeneralSecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//
//	}
//	
//	public void setSession()
//	{
//		session = Session.getInstance(properties,
//				new javax.mail.Authenticator() {
//					protected PasswordAuthentication getPasswordAuthentication() {
//						return new PasswordAuthentication(username,password);
//					}
//				});
//	}
//	
//	public String getEmailTo()
//	{
//		return emailTo;
//	}
//	
//	public String getEmailFrom()
//	{
//		return emailFrom;
//	}
//	
//	public String getHost()
//	{
//		return host;
//	}
//	
//	public Properties getObjProperties()
//	{
//		return properties;
//	}
//	
//	public Session getCurrentSession()
//	{
//		return session;
//	}
//	
//	public void sendEmail(String subject, String msg)
//	{		
//		try {
//
//			Message message = new MimeMessage(session);
//			message.setFrom(new InternetAddress(emailFrom));
//			message.setRecipients(Message.RecipientType.TO,
//				InternetAddress.parse(emailTo));
//			message.setSubject(subject);
//			message.setText(msg);
//			Transport transport = session.getTransport("smtps");
//			transport.send(message);
//
//			System.out.println("Done");
//
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		}
//		
//	}
//
//}