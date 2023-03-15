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

	String emailNote = "example@wp.pl";
	String host = "smtp.wp.pl";
	String login = "exampleLogin";
	String password = "examplePassword";

	/**
	 * To make this method working, you must change emailNote, host, login and password for correct.
	 * Probably you should also change SNTP port in properties inside the method.
	 */
	@Override
	public void sendMail(String to, String subject, String body) {
		
		
//		String from = emailNote; 
//
//		Properties properties = new Properties();
//		properties.put("mail.smtp.host", host);
//		properties.put("mail.smtp.port", "465");
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
//		properties.setProperty("mail.smtp.port", "465");
//		properties.setProperty("mail.smtp.sender.address", from);
//		properties.setProperty("mail.smtp.socketFactory.port", "465");
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
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//			message.setSubject(subject);
//			message.setText(body);
//
//			Transport.send(message);
//		} catch (MessagingException mex) {
//			mex.printStackTrace();
//		}

	}

	@Override
	public void newUser(User user) {
		String subject = "New user";
		String body = "New user has been register: " + "\n" + user.getFirstName() + " " + user.getLastName()
				+ ", telefon: " + user.getPhoneNumber() + ", email: " + user.getEmail();

		sendMail(emailNote, subject, body);
	}

	@Override
	public void sendActivateCode(Integer userId) {
		Optional<User> user = repo.findById(userId);

		if (user.isPresent()) {
			String email = user.get().getEmail();
			String actCode = user.get().getActivateNum().toString();
			String subject = "Activation code";
			String body = "Your actiation code is: " + actCode;

			sendMail(email, subject, body);
		}
	}


	
	



	@Override
	public void newMessage(String date, String firstName, String lastName, String email, String phoneNumber,
			String subject, String body) {
		MessageSend message = new MessageSend(date, firstName, lastName, email, phoneNumber, subject, body);
		mesRepo.save(message);
		String subject2 = "Your message has been send";
		String body2 = "Thank you for your message. We will respond as soon as possible.\n \n"
				+ message;

		sendMail(email, subject2, body2);

		String subject3 = "New message";
		String body3 = "\n" + message;

		sendMail(emailNote, subject3, body3);
	}

	@Override
	public void findAll(ModelMap model) {
		model.addAttribute("messages", mesRepo.findAll());
	}



	@Override
	public MessageSend findById(Integer id) {
return mesRepo.findById(id).orElse(new MessageSend());
	}


}
