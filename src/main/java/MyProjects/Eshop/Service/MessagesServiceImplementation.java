package MyProjects.Eshop.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import MyProjects.Eshop.Model.MessageSend;
import MyProjects.Eshop.Model.User;
import MyProjects.Eshop.Repository.MessageSendRepository;

@Service
public class MessagesServiceImplementation implements MessagesService {

	private MessageSendRepository mesRepo;

	public MessagesServiceImplementation(MessageSendRepository mesRepo) {
		super();
		this.mesRepo = mesRepo;
	}

	@Override
	public List<MessageSend> findReceived(User user) {
		return mesRepo.findByTo(user);
	}

	@Override
	public List<MessageSend> findSent(User user) {
		return mesRepo.findByFrom(user);
	}

	@Override
	public String findDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		return dtf.format(now);
	}

	@Override
	public void sendMessage(User from, User to, String subject, String body) {
		 String date = findDate();
		MessageSend message = new MessageSend(date, from, to, subject, body);
		mesRepo.save(message);
	}

	@Override
	public MessageSend findById(Integer messId) {
		return mesRepo.findById(messId).orElse(new MessageSend());
	}



}
