package MyProjects.Eshop.Controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import MyProjects.Eshop.Service.MailService;
import MyProjects.Eshop.Service.RoleService;
import MyProjects.Eshop.Service.UserService;
import MyProjects.Eshop.Model.PreparedMail;
import MyProjects.Eshop.Model.User;


@Controller
public class LoginAndRegisterController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailService mailService;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private RoleService roleService;

	@GetMapping("/login")
	public String login(ModelMap model) {
		model.addAttribute("user_isLogged",	userService.checkIfUserLogged());

		return "login";
	}

	@GetMapping("/register")
	public String register(ModelMap model) {
		model.addAttribute("user_isLogged",	userService.checkIfUserLogged());

		return "register";
	}

	@PostMapping("/register")
	public String registerSubmit(@RequestParam String passwordConfirmed, @ModelAttribute("user") User user,
			ModelMap model) {
		user.setUsername(user.getUsername().toLowerCase());
		

		if(userService.alreadyExist(user)) {
			model.addAttribute("message", "This user name already exists");
			return "register";
		}
		
		if(!userService.checkPassword(user, passwordConfirmed)) {
			model.addAttribute("message", "Passwords doesn't match");
			return "register";
		}
		String newPass = encoder.encode(user.getPassword());
		userService.registerNew(user,newPass);
		
		
		model.addAttribute("userId", user.getId());
				mailService.sendMail(mailService.prepareActivateCode(user));

		
	return "activate";
	}
	

	@GetMapping("/activate")
	public String goToActivate(ModelMap model) {
		model.addAttribute("user_isLogged",	userService.checkIfUserLogged());

		return "activate";
	}
	
	@PostMapping("/activate")
	public String activateTheCode(@RequestParam Integer userId, @RequestParam Integer filCode, ModelMap model) {
	
		
		String returnStr =	userService.activateCode(userId, filCode);
			

		model.addAttribute("userId", userId);
		return returnStr;
	}




}
