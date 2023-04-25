package MyProjects.Eshop.Controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import MyProjects.Eshop.App;
import MyProjects.Eshop.Model.User;
import MyProjects.Eshop.Service.RoleService;
import MyProjects.Eshop.Service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = App.class)
public class test_LoginAndRegistrationController {
	@Autowired
	MockMvc mockMvc;

	@MockBean
	private UserService userService;
	

	@MockBean
	User user;
	@MockBean
	RoleService roleService;
	


	@Test
	void test_goToLogin() throws Exception {

		mockMvc.perform(get("/login")).andExpect(status().isOk()).andExpect(view().name("login"));
	}

	@Test
	void test_GoToRegistraion() throws Exception {

		mockMvc.perform(get("/register")).andExpect(status().isOk()).andExpect(view().name("register"));
	}

//	@Test
//	@WithMockUser
//	void test_registerSubmit_false() throws Exception {
//		User testUser = new User();
//		testUser.setUsername("test");
//
//		User foundUser = new User();
//		Optional<User> optUser= Optional.of(foundUser);
//		String pass="pass";
//String name = "name";
//when(userService.findByUsername(name)).thenReturn(optUser);
//
//		when(userService.checkRegister(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(false);
//		mockMvc.perform(post("/register")).andExpect(status().isOk()).andExpect(view().name("register"));
//
//	}
//	
//	@Test
//	void test_registerSubmit_true() throws Exception {
//
//		when(userService.checkRegister(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
//
//		mockMvc.perform(post("/register")).andExpect(status().isOk()).andExpect(view().name("activate"));
//
//	}
}
