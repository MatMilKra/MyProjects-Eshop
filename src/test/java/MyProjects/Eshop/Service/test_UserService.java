package MyProjects.Eshop.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
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
import MyProjects.Eshop.Model.Role;
import MyProjects.Eshop.Model.ShopItem;
import MyProjects.Eshop.Model.User;
import MyProjects.Eshop.Repository.RoleRepository;
import MyProjects.Eshop.Repository.UserRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class test_UserService {
	@InjectMocks
	private UserServiceImplementation userService;
	
	@MockBean
	private UserRepository userRepo;
	@MockBean
	private RoleRepository roleRepo;
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
	
@Test
public void test_setRole() {
	Role role = new Role();
	User user = new User("a","b","c","d","e",role);
	Optional<User> userOp = Optional.of(user);
	
	Mockito.when(userRepo.findById(Mockito.anyInt())).thenReturn(userOp);
	Mockito.when(roleRepo.findByRolename(Mockito.anyString())).thenReturn(role);
	userService.setRole(1, "role");
	Mockito.verify(userRepo).save(user);
}





}
