package MyProjects.Eshop.Controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import MyProjects.Eshop.App;
import MyProjects.Eshop.Model.User;
import MyProjects.Eshop.Service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = App.class)
public class test_ProfileController {
	@Autowired
	MockMvc mockMvc;

	@MockBean
	private UserService userService;
	

	@MockBean
	User user;
	
	@Test
	public void test_goIndex() throws Exception {
		
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));

	}
	
	@Test
	@WithMockUser
	public void test_goToProfile() throws Exception {
		User user = new User();
		when(userService.getCurrentUser()).thenReturn(user);
		mockMvc.perform(get("/profile")).andExpect(status().isOk()).andExpect(view().name("profile"));

	}
	
	@Test
	@WithMockUser
	public void test_updateProfile() throws Exception {
		User user = new User();
		when(userService.getCurrentUser()).thenReturn(user);
		mockMvc.perform(post("/profile")).andExpect(status().isOk()).andExpect(view().name("profile"));
	}
	
	@Test
	@WithMockUser
	public void test_goTUupdateProfile() throws Exception {
		User user = new User();
		when(userService.getCurrentUser()).thenReturn(user);
		mockMvc.perform(get("/goToUpdateProfile")).andExpect(status().isOk()).andExpect(view().name("updateProfile"));
	}
}
