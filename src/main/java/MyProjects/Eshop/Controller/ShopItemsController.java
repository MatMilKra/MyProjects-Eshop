package MyProjects.Eshop.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import MyProjects.Eshop.Model.ShopItem;
import MyProjects.Eshop.Model.User;
import MyProjects.Eshop.Service.ShopItemService;
import MyProjects.Eshop.Service.UserService;

@Controller
public class ShopItemsController {

	@Autowired
	private ShopItemService shopItemService;
	@Autowired
	private ProfileController profileController;

	@Autowired
	private UserService userService;

	public ShopItemsController() {
	}

	@Autowired
	public ShopItemsController(ShopItemService service) {
		this.shopItemService = service;
	}

	public void checkUserLogged(ModelMap model) {

		profileController.checkUserLogged(model);
	}

	static List<String> categories = null;

	static {
		categories = new ArrayList<>();
		categories.add("Instrument");
		categories.add("Cloth");
		categories.add("Vehicle");
		categories.add("For animals");
		categories.add("Other");

	}

	public void setCategories(ModelMap model) {
		model.addAttribute("categories", categories);

	}

	@GetMapping(value = "/add")
	public String createNewItem(ModelMap model) {
		checkUserLogged(model);
		setCategories(model);

		return "add";
	}

	@PostMapping(value = "/add")
	public String createNewItem(Authentication authentication, ModelMap model, @RequestParam MultipartFile[] imageFile,
			@RequestParam String name, @RequestParam String category, @RequestParam String price,
			@RequestParam String description, @RequestParam String amount) {
		checkUserLogged(model);
		setCategories(model);

		Double priceDouble = 0.0;
		try {
			priceDouble = Double.parseDouble(price);
		} catch (NumberFormatException nfe) {
			model.addAttribute("message", "Invalid price");
			return "/add";

		}

		Integer amountInt = 0;
		try {
			amountInt = Integer.parseInt(amount);
		} catch (NumberFormatException nfe) {
			model.addAttribute("message", "Invalid amount");
			return "/add";

		}
		User owner = userService.getCurrentUser();
		shopItemService.createNewItem(name, description, category, priceDouble, amountInt, owner, imageFile);
		populateModel(model);
		model.addAttribute("message", "Item has been added");

		return "/add";
	}

	@GetMapping(value = "/search")
	public String searchItem(ModelMap model) {

		checkUserLogged(model);

		setCategories(model);

		return "search";

	}

	@PostMapping(value = "/search")
	public String searchItem(ModelMap model, @RequestParam String searchTab, @RequestParam String category,
			@RequestParam String priceMin, @RequestParam String priceMax) {

		checkUserLogged(model);
		Double doubleMin = 0.0;
		Double doubleMax = 0.0;
		if (!priceMin.isEmpty()) {
			try {
				doubleMin = Double.parseDouble(priceMin);
			} catch (NumberFormatException nfe) {
				model.addAttribute("message", "Invalid minimum price");
				return "/search";

			}
		}
		if (!priceMax.isEmpty()) {

			try {
				doubleMax = Double.parseDouble(priceMax);
			} catch (NumberFormatException nfe) {
				model.addAttribute("message", "Invalid maximum price");
				return "search";
			}
		}
		model.addAttribute("items", shopItemService.findItem(searchTab, category, doubleMin, doubleMax));

		setCategories(model);
		return "search";
	}



	private void populateModel(ModelMap model) {
		model.addAttribute("items", shopItemService.findAllItems());
	}

	@GetMapping(value = "/item/{id}")
	public String goToDetails(ModelMap model, @PathVariable int id) {
		checkUserLogged(model);
		Optional<ShopItem> item = shopItemService.findById(id);
		if (item.isPresent()) {
			ShopItem shopItem = item.get();
			model.addAttribute("item", shopItem);
		}
		
		return "details";
	}

	@PostMapping(value = "/addToCart")
	public String addToCart(ModelMap model, @RequestParam int itemId) {
		checkUserLogged(model);

		shopItemService.addToCart(model, itemId);
		goToDetails(model, itemId);

		return "details";

	}

	@PostMapping(value = "/listByVendor")
	public String listByOwner(ModelMap model, @RequestParam int id) {
		checkUserLogged(model);
//		Integer userId=Integer.parseInt(id);
		User user = userService.findById(id);
		model.addAttribute("items", shopItemService.filterByVendor(user));
		return "myItems";
	}

	@GetMapping(value = "/myItems")
	public String myItems(ModelMap model) {
		checkUserLogged(model);
		User user = userService.getCurrentUser();
		model.addAttribute("items", shopItemService.filterByVendor(user));
		return "myItems";
	}

	@GetMapping(value = "/myCart")
	public String myCart(ModelMap model) {
		checkUserLogged(model);
		User user = userService.getCurrentUser();
		List<ShopItem> myCart = user.getCartItems();
		model.addAttribute("myCart", myCart);
		Double totalPrice = 0.0;
		for (ShopItem item : myCart) {
			totalPrice += item.getPrice();
		}
		model.addAttribute("totalPrice", totalPrice);

		return "myCart";
	}

	@GetMapping("/buy")
	public String buy(ModelMap model) {
		List<ShopItem> noItem = new ArrayList<>();

		noItem = shopItemService.checkAvailable();
		if (noItem.size() >= 1) {
			model.addAttribute("noItem", noItem);
			model.addAttribute("message", "Some items are no more available. Please delete from cart:");
			myCart(model);
			return "myCart";
		} else {
			shopItemService.buy();

		}

		profileController.goToProfile(model);

		return "profile";
	}

	@PostMapping("/buyed")
	public String seeMyOrdersPost(ModelMap model) {
		checkUserLogged(model);

		User user = userService.getCurrentUser();
		model.addAttribute("buyed", shopItemService.getBuyed(user));
		return "bought";
	}

	@PostMapping("/deleteFromCart")
	public String deleteFromCart(ModelMap model, @RequestParam int itemId) {
		checkUserLogged(model);
		shopItemService.deleteFromCart(itemId);
		return "myCart";
	}
}
