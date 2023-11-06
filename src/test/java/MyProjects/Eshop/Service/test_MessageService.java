package MyProjects.Eshop.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import MyProjects.Eshop.Model.MessageSend;
import MyProjects.Eshop.Model.User;
import MyProjects.Eshop.Repository.MessageSendRepository;

@ExtendWith(MockitoExtension.class)
public class test_MessageService {
	@Mock
	MessageSendRepository messRepo;
	@InjectMocks
	private MessagesServiceImplementation messServ;
	User user;
	DateTimeFormatter dtf;
	LocalDateTime now;

	@BeforeEach
	void setUp() {
		user = new User();
		dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		now = LocalDateTime.now();
	}

	@Test
	public void test_findReceiver() {

		messServ.findReceived(user);
		Mockito.verify(messRepo).findByTo(user);

	}

	@Test
	public void test_findSent() {
		messServ.findSent(user);
		Mockito.verify(messRepo).findByFrom(user);

	}

	@Test
	public void test_findById() {
		messServ.findById(1);
		Mockito.verify(messRepo).findById(1);

	}

	@Test
	public void test_findDate() {

		String data = messServ.findDate();
		assertEquals(dtf.format(now), data);
	}

	@Test
	public void test_sendMessage() {

		String date = dtf.format(now);
		User user1 = new User();
		String sub = "";
		String body = "";
		MessageSend message = new MessageSend(date, user, user1, sub, body);
		messServ.sendMessage(user, user1, sub, body);
		verify(messRepo).save(message);

	}
}
