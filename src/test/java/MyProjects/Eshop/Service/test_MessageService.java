package MyProjects.Eshop.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
	
	@Test
	public void test_findReceiver() {
		User user = new User();
		messServ.findReceived(user);
		Mockito.verify(messRepo).findByTo(user);
		
	}
	
	@Test
	public void test_findSent() {
		User user = new User();
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
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String data=messServ.findDate();
		assertEquals(dtf.format(now), data);
	}
	
	@Test
	public void test_sendMessage() {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		User user1=new User();
		User user2=new User();
		String sub = "";
		String body = "";
	//	Mockito.when(messServ.findDate()).thenReturn(date);
		MessageSend message = new MessageSend(date,user1,user2,sub,body);
		messServ.sendMessage(user1, user2, sub, body);
	Mockito.verify(messRepo).save(message);
	
	}
}
