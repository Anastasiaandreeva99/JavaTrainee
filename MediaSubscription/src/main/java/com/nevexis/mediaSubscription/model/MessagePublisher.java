package com.nevexis.mediaSubscription.model;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class MessagePublisher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@ManyToMany
	List<MessageSubscriber> subscribers;

	public MessagePublisher() {
		subscribers = new ArrayList<>();
	}

	public void subscribe(MessageSubscriber subscriber) {
		if (null == subscribers)
			subscribers = new ArrayList<>();
		if (!subscribers.contains(subscriber)) {
			subscribers.add(subscriber);
		}
	}

	public void unsubscribe(MessageSubscriber subscriber) {
		subscribers.remove(subscriber);
	}

	public void publish(Message m) {
		subscribers.stream().forEach(el -> el.update(m));
	}
	
	private void sendmail(Message m) throws AddressException, MessagingException, IOException {
		   Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("anastasia.i.a.sisi@gmail.com", "<your password>");
		      }
		   });
		   MimeMessage msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("anastasia.i.a.sisi@gmail.com", false));

		   msg.setRecipients( MimeMessage.RecipientType.TO, InternetAddress.parse("anastasia.i.a.sisi@gmail.com"));
		   msg.setSubject(m.getAbout());
		   msg.setContent(m.getHeader()+m.getFrom(), "text/html");
		   

		   MimeBodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setContent(m.getMainText(), "text/html");

		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(messageBodyPart);

		   msg.setContent(multipart);
		   Transport.send(msg);   
		}
}



