package MyProjects.Eshop.Service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;

import MyProjects.Eshop.Model.MessageSend;
import MyProjects.Eshop.Model.User;
import MyProjects.Eshop.Repository.UserRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class test_UserService {
	@InjectMocks
	private UserServiceImplementation userService;
	
	@MockBean
	private UserRepository userRepo;
	
	@MockBean
	ModelMap model;

	

	
	@Test
	public void test_save() {
		User user = new User();
		userService.save(user);
		Mockito.verify(userRepo).save(user);
	}

	
	@Test
	public void test_updateUser() {
		User user1 = new User();
		User user2 = new User();
		userService.updateUser(user1, user2);
		Mockito.verify(userRepo,times(1)).save(user2);
		
	}
	
	@Test
	public void test_findById() {
		userService.findById(1);
		Mockito.verify(userRepo).findById(1);
		
	}
	

}
