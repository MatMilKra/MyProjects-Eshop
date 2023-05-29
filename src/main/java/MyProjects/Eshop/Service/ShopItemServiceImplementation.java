package MyProjects.Eshop.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import MyProjects.Eshop.Model.Picture;
import MyProjects.Eshop.Model.ShopItem;
import MyProjects.Eshop.Model.User;
import MyProjects.Eshop.Repository.ShopItemRepository;
import MyProjects.Eshop.Repository.UserRepository;

@Service
public class ShopItemServiceImplementation implements ShopItemService {

	private ShopItemRepository shopItemRepo;
	private UserRepository userRepo;
	private UserService userServ;

	@Autowired
	public ShopItemServiceImplementation(ShopItemRepository shopItemRepo, UserRepository userRepo,
			UserService userServ) {
		super();
		this.shopItemRepo = shopItemRepo;
		this.userRepo = userRepo;
		this.userServ = userServ;
	}

	@Override
	public String createNewItem(ModelMap model, String name, String description, String category, String price, String amount,
			User owner, MultipartFile[] imageFile) {
		String message="";
		Double priceDouble = 0.0;
		try {
			priceDouble = Double.parseDouble(price);
		} catch (NumberFormatException nfe) {
			message= "Invalid price";
			return message;

		}

		Integer amountInt = 0;
		try {
			amountInt = Integer.parseInt(amount);
		} catch (NumberFormatException nfe) {
			message="Invalid amount";
			return message;

		}
		ShopItem item = new ShopItem(name, description, category, priceDouble, amountInt, owner);
		InputStream inputStream;
		List<Picture> pictures = new ArrayList<>();
		for (MultipartFile image : imageFile) {
			if (!image.getOriginalFilename().isEmpty()) {
				Picture picture = new Picture(image.getOriginalFilename());
				pictures.add(picture);
				Path destinationFile = Paths.get(System.getProperty("user.dir"), "src/main/webapp/item-pictures",
						image.getOriginalFilename());
				try {
					inputStream = image.getInputStream();
					Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();

				}
			}
		}
		item.setPictures(pictures);
		shopItemRepo.save(item);
		
		message="Item has been added";

		
		return message;

	}

	@Override
	public List<ShopItem> findAllItems() {

		return shopItemRepo.findAll();
	}

	@Override
	public Optional<ShopItem> findById(int id) {
		return shopItemRepo.findById(id);
	}

	@Override
	public List<ShopItem> filterByVendor(User user) {
		return shopItemRepo.findByVendor(user);
	}

	@Override
	public List<ShopItem> findItem(ModelMap model, 
			String searchTab, String category,
			String priceMin, String priceMax) {
		List<ShopItem> items = new ArrayList<>();

		Double doubleMin = 0.0;
		Double doubleMax = 0.0;
		if (!priceMin.isEmpty()) {
			try {
				doubleMin = Double.parseDouble(priceMin);
			} catch (NumberFormatException nfe) {
				model.addAttribute("message", "Invalid minimum price");
				return items;

			}
		}
		if (!priceMax.isEmpty()) {

			try {
				doubleMax = Double.parseDouble(priceMax);
			} catch (NumberFormatException nfe) {
				model.addAttribute("message", "Invalid maximum price");
				return items;
			}
		}
		List<ShopItem> help = new ArrayList<>();
		if (searchTab.isEmpty())
			items = findAllItems();
		else {
			items = shopItemRepo.findByNameContainsIgnoreCase(searchTab);
			items.addAll(shopItemRepo.findByDescriptionContainsIgnoreCase(searchTab));
			items = items.stream().distinct().collect(Collectors.toList());
		}
		if (!category.isEmpty()) {
			help = shopItemRepo.findByCategoryContainsIgnoreCase(category);
			items = items.stream().distinct().filter(help::contains).collect(Collectors.toList());
		}
		if (doubleMin > 0) {
			help = shopItemRepo.findByPriceGreaterThanEqual(doubleMin);
			items = items.stream().distinct().filter(help::contains).collect(Collectors.toList());
		}
		if (doubleMax > 0) {
			help = shopItemRepo.findByPriceLessThanEqual(doubleMax);
			items = items.stream().distinct().filter(help::contains).collect(Collectors.toList());
		}

		return items;
	}

	@Override
	public void addToCart(ModelMap model, int id) {
		Optional<ShopItem> itemOp = findById(id);
		ShopItem item = new ShopItem();
		
		if (itemOp.isPresent())
			item = itemOp.get();
		
		User user = userServ.getCurrentUser();
		User vendor = item.getVendor();
		
		if (vendor == user) {
			model.addAttribute("message", "This is your item. You cannot buy it.");
		} else if (item.getAmount() <= 0) {
			model.addAttribute("message", "This item is no longer available");
		} else {
			user.addToCart(item);
			userRepo.save(user);
		}
	}

	@Override
	public List<ShopItem> getCart(User user) {
		return user.getCartItems();
	}

	@Override
	public List<ShopItem> getBuyed(User user) {
		return user.getBuyedIytems();
	}

	@Override
	public void buy() {
		User user = userServ.getCurrentUser();

		List<ShopItem> cart = user.getCartItems();
		List<ShopItem> bought = user.getBuyedIytems();
		ShopItem item = new ShopItem();
		for (int i = 0; i < cart.size(); i++) {
			item = cart.get(i);
			bought.add(item);
			cart.remove(item);
			item.setAmount(item.getAmount() - 1);
			shopItemRepo.save(item);
		}

		userRepo.save(user);
	}

	@Override
	public List<ShopItem> checkAvailable() {
		User user = userServ.getCurrentUser();
		List<ShopItem> cart = getCart(user);
		List<ShopItem> noItem = new ArrayList<>();
		for (ShopItem item : cart) {
			if (item.getAmount() <= 0) {
				noItem.add(item);
			}
		}

		return noItem;
	}

	@Override
	public void deleteFromCart(int itemId) {
		User user = userServ.getCurrentUser();

		Optional<ShopItem> itemOp = findById(itemId);
		if (itemOp.isPresent())
			user.getCartItems().remove(itemOp.get());
		userRepo.save(user);
	}

	@Override
	public Double getTotalPrice(List<ShopItem> myCart) {
		Double totalPrice = 0.0;
		for (ShopItem item : myCart) {
			totalPrice += item.getPrice();
		}		return totalPrice;
	}

	@Override
	public List<ShopItem> getCartItems(User user) {
		return user.getCartItems();
	}

}
