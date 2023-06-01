package MyProjects.Eshop.Controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;

import MyProjects.Eshop.App;
import MyProjects.Eshop.Model.Role;
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
	@MockBean
	ModelMap model;
	


	@Test
	void test_goToLogin() throws Exception {

		mockMvc.perform(get("/login")).andExpect(status().isOk()).andExpect(view().name("login"));
	}

	@Test
	void test_GoToRegistraion() throws Exception {

		mockMvc.perform(get("/register")).andExpect(status().isOk()).andExpect(view().name("register"));
	}
	
	@Test
	void test_registerSubmit_badUsername() throws Exception {
		user=new User();
		user.setPassword(" ");
		user.setUsername(" ");
		Mockito.when(userService.alreadyExist(user)).thenReturn(true);
		mockMvc.perform(post("/register")
		.param("passwordConfirmed"," ").param("username", " ")).andExpect(status().isOk()).andExpect(view().name("register"));
	}

	@Test
	void test_registerSubmit_badPassword() throws Exception {
		user=new User();
		user.setPassword(" ");
		user.setUsername(" ");
		Mockito.when(userService.alreadyExist(user)).thenReturn(false);

		Mockito.when(userService.checkPassword(user," ")).thenReturn(false);
		mockMvc.perform(post("/register")
		.param("passwordConfirmed"," ").param("username", " ").param("passeord", " ")).andExpect(status().isOk()).andExpect(view().name("register"));
	}
	


}
