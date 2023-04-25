package MyProjects.Eshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import MyProjects.Eshop.Model.User;
import MyProjects.Eshop.Service.UserService;

@Controller
public class ProfileController {
	private UserService userService;

	@Autowired
	public ProfileController(UserService userService) {
		this.userService = userService;

	}

	public void checkInfo(ModelMap model) {
		userService.checkInfo(model);

	}
	
	public void checkUserLogged(ModelMap model) {
		model.addAttribute("user_isLogged", userService.checkIfUserLogged());
	}

	@GetMapping("/")
	public String goIndex(ModelMap model) {
		checkInfo(model);
		checkUserLogged(model);

		return "index";
	}

	@GetMapping("/profile")
	public String goToProfile(ModelMap model) {
		userService.populateUser(model);
		checkInfo(model);
		checkUserLogged(model);
		return "profile";
	}

	@PostMapping("/profile")
	public String updateProfile(ModelMap model, @ModelAttribute("user") User user) {
		User currUser = userService.getCurrentUser();

		userService.updateUser(user, currUser);
		userService.populateUser(model);
		checkInfo(model);
		checkUserLogged(model);


		return "profile";
	}



	@GetMapping("/goToUpdateProfile")
	public String goToUpdateProfile(ModelMap model) {
		userService.populateUser(model);
		checkUserLogged(model);

		return "updateProfile";
	}



}