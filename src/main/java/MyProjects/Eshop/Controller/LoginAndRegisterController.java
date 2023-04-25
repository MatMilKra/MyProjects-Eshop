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
		Optional<User> userFromDatabase = userService.findByUsername(user.getUsername());
		
		if (!userService.checkRegister(model,user, userFromDatabase, passwordConfirmed)) {
			return "register";
		}
		
		user.setRole(roleService.findByRoleName("Customer"));
		user.setPassword(encoder.encode(user.getPassword()));
		user.setActivateNum(randomNumber_find());
		userService.save(user);
		
		mailService.sendActivateCode(user.getId());
		model.addAttribute("userId", user.getId());
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

	public Integer randomNumber_find() {
		Integer actNum = (int) (Math.random() * 900000 + 100000);
		return actNum;
	}


}
