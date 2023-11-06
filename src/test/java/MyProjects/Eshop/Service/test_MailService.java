package MyProjects.Eshop.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import MyProjects.Eshop.Model.User;

@ExtendWith(MockitoExtension.class)
public class test_MailService {
	@InjectMocks
	private MailServiceImplementation mailServ;

	@Test
	public void test_newUser() {
		User user = new User();
		user.setFirstName("A");
		user.setLastName("B");
		user.setEmail("a@b.c");
		user.setPhoneNumber("123");
		mailServ.newUser(user);
	}

	@Test
	public void test_sendActivateCode() {
		User user = new User();

		user.setEmail("a@b.c");
		user.setActivateNum(123);
		
		mailServ.sendActivateCode(user);
	}

	@Test
	public void test_newMessage() {

		mailServ.newMessage("a", "b", "c", "d@e.f", "g", "h", "i");
	}

	@Test
	public void test_sensMessage() {

		mailServ.sendMail("d@e.f", "h", "i");
	}

}
