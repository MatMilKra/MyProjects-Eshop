package MyProjects.Eshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import MyProjects.Eshop.Model.User;
import MyProjects.Eshop.Service.ShopItemService;
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
		populateUser(model);
		checkInfo(model);
		checkUserLogged(model);
		return "profile";
	}

	@PostMapping("/profile")
	public String updateProfile(ModelMap model, @ModelAttribute("user") User user) {
		User currUser = userService.getCurrentUser();

		userService.updateUser(user, currUser);
		populateUser(model);
		checkInfo(model);
		checkUserLogged(model);


		return "profile";
	}

	public void populateUser(ModelMap model) {
		if (userService.checkIfUserLogged()) {
			User user = userService.getCurrentUser();
			model.addAttribute("userUsername", user.getUsername());
			model.addAttribute("userFirstName", user.getFirstName());
			model.addAttribute("userLastName", user.getLastName());
			model.addAttribute("userEmail", user.getEmail());
			model.addAttribute("userPhoneNumber", user.getPhoneNumber());
		}

	}

	@PostMapping("/goToUpdateProfile")
	public String goToUpdateProfile(ModelMap model) {
		populateUser(model);
		checkUserLogged(model);

		return "updateProfile";
	}

	@GetMapping("/admin/panel")
	public String openPanel(ModelMap model) {

		model.addAttribute("user_isLogged", userService.checkIfUserLogged());

		return "admin/panel";
	}

}