package MyProjects.Eshop.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import MyProjects.Eshop.Model.MessageSend;
import MyProjects.Eshop.Model.User;
import MyProjects.Eshop.Service.MessagesService;
import MyProjects.Eshop.Service.UserService;

@Controller
public class MessagesController {

	@Autowired
	private ProfileController profileController;
	@Autowired
	private UserService userService;

	@Autowired
	private MessagesService messagesService;

	public MessagesController() {
		super();
	}

	public MessagesController(ProfileController profileController, MessagesService messagesService,
			UserService userService) {
		super();
		this.profileController = profileController;
		this.messagesService = messagesService;
		this.userService = userService;
	}

	public void checkUserLogged(ModelMap model) {

		profileController.checkUserLogged(model);
	}

	@GetMapping("/messages")
	public String messages(ModelMap model) {
		checkUserLogged(model);
		User user = userService.getCurrentUser();
		model.addAttribute("messages", messagesService.findReceived(user));
		return "messages";
	}

	@GetMapping("/messagesSent")
	public String messagesSent(ModelMap model) {
		checkUserLogged(model);
		User user = userService.getCurrentUser();
		model.addAttribute("messages", messagesService.findSent(user));
		return "messages";
	}

	@PostMapping("/newMessage")
	public String newMessage(ModelMap model, @RequestParam(required = false) String username) {
		checkUserLogged(model);

		model.addAttribute("username", username);
	

		return "newMessage";
	}
	
	@PostMapping("/messageReply")
	public String messageReply(ModelMap model, @RequestParam(required = false) String username,
			 String subject,
			String body) {
		checkUserLogged(model);

		model.addAttribute("username", username);
		
		model.addAttribute("subject", "Re: "+subject);
	
		model.addAttribute("body", "Reply to message:\n"
				+ "==============================\n"
				+body);

		return "newMessage";
	}

	@PostMapping("/sendMessage")
	public String sendMessage(ModelMap model, @RequestParam String username, @RequestParam String subject,
			@RequestParam String body) {
		checkUserLogged(model);
		User from = userService.getCurrentUser();
		Optional<User> user = userService.findByUsername(username);
		User to = new User();
		if (user.isPresent()) {
			to = user.get();
			messagesService.sendMessage(from, to, subject, body);
		} else {
			model.addAttribute("message", "That user doesn't exist");
			return "newMessage";

		}
//		}
		return "newMessage";
	}

	@PostMapping("/message")
	public String messageDetails(ModelMap model, @RequestParam Integer messId) {
		MessageSend message = messagesService.findById(messId);
		Boolean currentUserFrom = userService.currentUserFrom(message);
		messagesService.setMessage(model, message, currentUserFrom);
				return "messageDetails";
	}
}
