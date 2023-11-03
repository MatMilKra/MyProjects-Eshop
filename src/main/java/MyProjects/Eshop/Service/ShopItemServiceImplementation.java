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
	public boolean parseDoublePossible(String string) {
		try {
			Double.parseDouble(string);
		} catch (NumberFormatException nfe) {
			return false;

		}
		return true;
	}

	@Override
	public boolean parseIntegerPossible(String string) {
		try {
			Integer.parseInt(string);
		} catch (NumberFormatException nfe) {
			return false;

		}
		return true;
	}

	@Override
	public void createNewItem(String name, String description, String category, double price,
			int amount, User owner, MultipartFile[] imageFile) {

		ShopItem item = new ShopItem(name, description, category, price, amount, owner);
		
		List<Picture> pictures = new ArrayList<>();
		
		for (MultipartFile image : imageFile) {
			if (!image.getOriginalFilename().isEmpty()) {
				Picture picture = new Picture(image.getOriginalFilename());
				pictures.add(picture);
				Path destinationFile = Paths.get(System.getProperty("user.dir"), "src/main/webapp/item-pictures",
						image.getOriginalFilename());
				try {
					InputStream inputStream = image.getInputStream();
					Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();

				}
			}
		}
		item.setPictures(pictures);
		shopItemRepo.save(item);

	}

	@Override
	public String checkVendorAndAvailable(User user, ShopItem item) {
		String returnStr = "accept";
		User vendor = item.getVendor();

		if (vendor == user) {
			returnStr = "vendor";
		} else if (item.getAmount() <= 0)
			returnStr = "amount";
		return returnStr;
	}

	@Override
	public void addToCart(User user, ShopItem item) {

		user.addToCart(item);
		userRepo.save(user);

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
	public void buy(User user) {

		List<ShopItem> cart = user.getCartItems();
		List<ShopItem> bought = user.getBuyedIytems();

		for (ShopItem item : cart) {
			item.setAmount(item.getAmount() - 1);
			bought.add(item);
			shopItemRepo.save(item);
		}
		cart.clear();

		userRepo.save(user);
	}

	@Override
	public List<ShopItem> checkAvailable(User user) {
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
	public void deleteFromCart(User user, ShopItem item) {

		user.getCartItems().remove(item);
		userRepo.save(user);
	}

	@Override
	public Double getTotalPrice(List<ShopItem> myCart) {
		Double totalPrice = 0.0;
		
		for (ShopItem item : myCart) {
			totalPrice += item.getPrice();
		}
		
		return totalPrice;
	}

}
