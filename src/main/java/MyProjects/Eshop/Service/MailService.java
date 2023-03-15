package MyProjects.Eshop.Service;

import org.springframework.ui.ModelMap;

import MyProjects.Eshop.Model.MessageSend;

import MyProjects.Eshop.Model.User;

public interface MailService {

	void sendMail(String to, String subject, String body);

	void newUser(User user);

	void newMessage(String date, String firstName, String lastName, String email, String phoneNumber, String subject,
			String body);

	void findAll(ModelMap model);

	MessageSend findById(Integer id);

	void sendActivateCode(Integer id);

}
