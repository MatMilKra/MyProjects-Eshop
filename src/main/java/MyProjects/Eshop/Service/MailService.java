package MyProjects.Eshop.Service;

import org.springframework.ui.ModelMap;

import MyProjects.Eshop.Model.MessageSend;
import MyProjects.Eshop.Model.PreparedMail;
import MyProjects.Eshop.Model.User;

public interface MailService {

	void sendMail(PreparedMail mail);

	PreparedMail newUser(User user);

	PreparedMail prepareActivateCode(User user);

	PreparedMail newMessageResponse(String date, String firstName, String lastName, String email, String phoneNumber,
			String subject, String body);

	PreparedMail newMessageInfo(String date, String firstName, String lastName, String email, String phoneNumber,
			String subject, String body);

}
