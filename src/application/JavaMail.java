package application;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMail {
	
	static void sendMail(String recipient, String emailMessage) throws Exception {
		// configuration is via a Java Properties object:
		Properties properties = new Properties(); //prima kljuc i vrijednost?
		properties.put("mail.smtp.auth", "true");      //definise da li je potrebna autentifikacija za server, u nasem slucaju ne treba za gmail
		//simple mail transfer protocol
		properties.put("mail.smtp.starttls.enable", "true");  //za tls enkripcije, za gmail mora biti true
		properties.put("mail.smtp.host", "smtp.gmail.com");  
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com"); //ovo sam dodala kasnije zbog..
		
		String myAccountEmail = "email";
		String password = "password";
		
		
		Session session = Session.getInstance(properties, new Authenticator() { 
			//Session get instance: returns the new session
			//session object stores all the inf of host like host name, username, password etc.
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, password);
			}
		});
		
		
		Message message = prepareMessage(session, myAccountEmail, recipient, emailMessage);
		
		Transport.send(message); //The javax.mail.Transport class provides method to send the message
		System.out.println("Message sent successfully");
	}

	private static Message prepareMessage(Session session, String myAccountEmail, String recipient, String emailMessage) {
		/*The javax.mail.Message class provides methods to compose the message. 
		 * But it is an abstract class so its subclass 
		 * javax.mail.internet.MimeMessage class is mostly used.*/
		//composing the message, we need to pass the session object
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("My Email from Java"); //is used to set the subject header field.
			//message.setText("Heyyy");
			message.setText(emailMessage);
			return message;
		} catch (Exception ex) {
			Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
