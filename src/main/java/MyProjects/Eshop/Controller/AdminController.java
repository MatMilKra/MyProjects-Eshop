package MyProjects.Eshop.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import MyProjects.Eshop.Model.User;
import MyProjects.Eshop.Service.ShopItemService;
import MyProjects.Eshop.Service.UserService;

@Controller
public class AdminController {
	private UserService userService;
	
	@Autowired
	private ShopItemService shopItemService;

	@Autowired
	public AdminController(UserService userService, ShopItemService shopItemService) {
		this.userService = userService;
		this.shopItemService = shopItemService;

	}
	
	static List<String> roleChoose=null;
	static {
		roleChoose=new ArrayList<>();
		roleChoose.add("Admin");
		roleChoose.add("Customer");
		
	}

	public void checkInfo(ModelMap model) {
		userService.checkInfo(model);

	}
	
	public void checkUserLogged(ModelMap model) {
		model.addAttribute("user_isLogged", userService.checkIfUserLogged());
	}
	
	@GetMapping("/admin/panel")
	public String openPanel(ModelMap model) {

checkUserLogged(model);
		return "admin/panel";
	}
	
	@GetMapping("/admin/users")
	public String seeusers(ModelMap model) {

		checkUserLogged(model);
		model.addAttribute("usersAll",userService.findAll());

		return "admin/users";
	}
	
	public void methodFor_users_Id_and_setRole_Id(Integer id, ModelMap model) {
		checkUserLogged(model);
		model.addAttribute("roleChoose", roleChoose);
		User user = userService.findById(id);
		model.addAttribute("user", user);
		
	}
	@GetMapping(value = "/admin/users/{id}")
	public String seeUsers(ModelMap model, @PathVariable int id) {
methodFor_users_Id_and_setRole_Id(id, model);
		
			

		return "admin/userDetails";
	}

	@PostMapping("/admin/setRole/{id}")
	public String setRole(ModelMap model,  @PathVariable int id, @RequestParam String roleChoose) {
		userService.setRole(id, roleChoose);
		methodFor_users_Id_and_setRole_Id(id, model);
		return "admin/userDetails";
	}
	
	@PostMapping(value = "/admin/userBought")
	public String listByOwner(ModelMap model, @RequestParam int id) {
		checkUserLogged(model);
		User user = userService.findById(id);
		model.addAttribute("userBought", user.getBuyedIytems());
		return "/admin/userBought";
	}

	

}