package MyProjects.Eshop.Service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import MyProjects.Eshop.Model.MessageSend;
import MyProjects.Eshop.Model.PreparedMail;
import MyProjects.Eshop.Model.User;
import MyProjects.Eshop.Repository.MessageSendRepository;
import MyProjects.Eshop.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class MailServiceImplementation implements MailService {

	private UserRepository repo;
	private MessageSendRepository mesRepo;

	@Autowired
	public MailServiceImplementation(UserRepository repo, MessageSendRepository mesRepo) {
		super();
		this.repo = repo;
		this.mesRepo = mesRepo;

	}

	String emailNote = "exampleLogin@wp.pl";
	String host = "smtp.wp.pl";
	String login = "exampleLogin";
	String password = "examplePassword";
	String port = "465";

	/**
	 * To make this method working, you must change emailNote, host, login and
	 * password for correct. Probably you should also change SNTP port in properties
	 * inside the method.
	 */
	@Override
	public void sendMail(PreparedMail mail) {

//		String from = emailNote; 
//
//		Properties properties = new Properties();
//		properties.put("mail.smtp.host", host);
//		properties.put("mail.smtp.port", port);
//		properties.put("mail.smtp.ssl.enable", "true");
//		properties.put("mail.smtp.auth", "true");
//
//		properties.put("mail.smtp.starttls.enable", "true");
//		properties.put("mail.smtp.EnableSSL.enable", "true");
//
//		properties.put("mail.debug", "true");
//
//		properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//		properties.setProperty("mail.smtp.socketFactory.fallback", "false");
//		properties.setProperty("mail.smtp.port", port);
//		properties.setProperty("mail.smtp.sender.address", from);
//		properties.setProperty("mail.smtp.socketFactory.port", port);
//
//		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(login,password);
//			}
//		});
//
//		try {
//			MimeMessage message = new MimeMessage(session);
//			message.setFrom(new InternetAddress(from));
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getTo()));
//			message.setSubject(mail.getSubject());
//			message.setText(mail.getBody());
//
//			Transport.send(message);
//		} catch (MessagingException mex) {
//			mex.printStackTrace();
//		}

	}

	@Override
	public PreparedMail newUser(User user) {
		String subject = "New user";
		String body = "New user has been register: " + "\n" + user.getFirstName() + " " + user.getLastName()
				+ ", telefon: " + user.getPhoneNumber() + ", email: " + user.getEmail();

		return new PreparedMail(emailNote, subject, body);
	}

	@Override
	public PreparedMail prepareActivateCode(User user) {

		String email = user.getEmail();
		Integer actCode = user.getActivateNum();
		String subject = "Activation code";
		String body = "Your actiation code is: " + actCode;

		return new PreparedMail(email, subject, body);

	}

	@Override
	public PreparedMail newMessageResponse(String date, String firstName, String lastName, String email,
			String phoneNumber, String subject, String body) {

		String subject2 = "Your message has been send";
		String body2 = "Thank you for your message. We will respond as soon as possible.\n \n" + "Date: " + date + "\n"
				+ "From: " + firstName + " " + lastName + "\n" + "Email: " + email + "\n" + "Telephone: " + phoneNumber
				+ "\n" + "Subject: " + subject + "\n" + "-----------------------\n " + body;
		return new PreparedMail(email, subject, body);
	}

	@Override
	public PreparedMail newMessageInfo(String date, String firstName, String lastName, String email, String phoneNumber,
			String subject, String body) {
		String subject1 = "New message";
		String body3 = "\n" + "Date: " + date + "\n" + "From: " + firstName + " " + lastName + "\n" + "Email: " + email
				+ "\n" + "Telephone: " + phoneNumber + "\n" + "Subject: " + subject + "\n"
				+ "-----------------------\n " + body;
		return new PreparedMail(emailNote, subject1, body);
	}

}
