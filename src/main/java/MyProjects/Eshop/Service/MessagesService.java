package MyProjects.Eshop.Service;
import java.util.List;

import org.springframework.ui.ModelMap;

import MyProjects.Eshop.Model.MessageSend;
import MyProjects.Eshop.Model.User;



public interface MessagesService {

	List<MessageSend> findReceived(User user);
	 String findDate();
	void sendMessage(User from, User to, String subject, String body);
	List<MessageSend> findSent(User user);
	MessageSend findById(Integer messId);

}
