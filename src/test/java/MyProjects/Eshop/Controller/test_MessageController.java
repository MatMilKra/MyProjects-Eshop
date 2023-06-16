package MyProjects.Eshop.Controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ModelMap;

import MyProjects.Eshop.App;
import MyProjects.Eshop.Model.MessageSend;
import MyProjects.Eshop.Model.ShopItem;
import MyProjects.Eshop.Model.User;
import MyProjects.Eshop.Service.MessagesServiceImplementation;
import MyProjects.Eshop.Service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = App.class)
public class test_MessageController {
	@MockBean
	UserService userService;
	@MockBean
	MessagesServiceImplementation messageService;
	@MockBean
	ModelMap model;
	@Autowired
	MockMvc mockMvc;
	
	User user;
	String subject;
	String body;
	
	@BeforeEach
	public void prepareData() {
		user= new User();
		subject = "sub";
		body = "body";
	}

	@Test
	@WithMockUser
	void test_messages() throws Exception {
		when(userService.getCurrentUser()).thenReturn(user);
		mockMvc.perform(get("/messages")).andExpect(status().isOk())
		.andExpect(view().name("messages"));
	}

	@Test
	@WithMockUser
	void test_messageSent() throws Exception {
		when(userService.getCurrentUser()).thenReturn(user);
		mockMvc.perform(get("/messagesSent")).andExpect(status().isOk())
		.andExpect(view().name("messages"));
	}
	
	@Test
	@WithMockUser
	void test_NewMessage() throws Exception {
		String username = "username";
		mockMvc.perform(post("/newMessage").param("username",username)).andExpect(status().isOk())
		.andExpect(model().attribute("username", username))
				.andExpect(view().name("newMessage"));
	}
	@Test
	@WithMockUser
	void test_messageReply() throws Exception {
		String username = "username";

		mockMvc.perform(post("/messageReply").param("username",username).param("subject", subject).param("body", body)).andExpect(status().isOk())
		.andExpect(model().attribute("username", username))
		.andExpect(model().attribute("subject", "Re: "+subject))
		.andExpect(model().attribute("body", "Reply to message:\n"
				+ "==============================\n"
				+body))
				.andExpect(view().name("newMessage"));
	}
	@Test
	@WithMockUser
	void test_sendMessage_opUserIsPresent() throws Exception {
		String username = "username";

		User opUser = new User();
		
		when(userService.getCurrentUser()).thenReturn(user);
		when(userService.findByUsername(username)).thenReturn(Optional.of(opUser));
		when(userService.optionalIsPresent(Optional.of(opUser))).thenReturn(true);
		mockMvc.perform(post("/sendMessage").param("username",username).param("subject", subject).param("body", body)).andExpect(status().isOk())
				.andExpect(view().name("newMessage"));
	}
	
	@Test
	@WithMockUser
	void test_sendMessage_opUserIsNotPresent() throws Exception {
		String username = "username";

		User opUser = new User();
		
		when(userService.getCurrentUser()).thenReturn(user);
		when(userService.findByUsername(username)).thenReturn(Optional.of(opUser));
		when(userService.optionalIsPresent(Optional.of(opUser))).thenReturn(false);
		mockMvc.perform(post("/sendMessage").param("username",username).param("subject", subject).param("body", body)).andExpect(status().isOk())
		.andExpect(model().attribute("message", "That user doesn't exist"))
.andExpect(view().name("newMessage"));
	}
	
	@Test
	@WithMockUser
	void test_messageDetails_currentUserFrom() throws Exception {

		User user2 = new User();
		MessageSend mess = new MessageSend();

		Integer messId = 1;
		mess.setSubject(subject);
		mess.setBody(body);
		mess.setFrom(user);
		mess.setTo(user2);
		when(messageService.findById(Mockito.anyInt())).thenReturn(mess);
		
		when(userService.currentUserFrom(mess)).thenReturn(true);
		
		mockMvc.perform(post("/message")
		.param("messId","1"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("subject", subject))
		.andExpect(model().attribute("body", body))
		.andExpect(view().name("messageDetails"));
	}
	
	@Test
	@WithMockUser
	void test_messageDetails_currentUserTo() throws Exception {

		User user2 = new User();
		MessageSend mess = new MessageSend();

		mess.setSubject(subject);
		mess.setBody(body);
		mess.setFrom(user2);
		mess.setTo(user);
		when(messageService.findById(Mockito.anyInt())).thenReturn(mess);
		
		when(userService.currentUserFrom(mess)).thenReturn(false);
		
		mockMvc.perform(post("/message")
		.param("messId","1"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("subject", subject))
		.andExpect(model().attribute("body", body))
		.andExpect(view().name("messageDetails"));
	}
	
}
