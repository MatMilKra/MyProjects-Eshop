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
	public void createNewItem(String name, String description, String category, Double price, Integer amount,
			User owner, MultipartFile[] imageFile) {
		ShopItem item = new ShopItem(name, description, category, price, amount, owner);
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
	public List<ShopItem> findItem(String searchTab, String category, Double priceMin, Double priceMax) {
		List<ShopItem> items = new ArrayList<>();
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
		if (priceMin > 0) {
			help = shopItemRepo.findByPriceGreaterThanEqual(priceMin);
			items = items.stream().distinct().filter(help::contains).collect(Collectors.toList());
		}
		if (priceMax > 0) {
			help = shopItemRepo.findByPriceLessThanEqual(priceMax);
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
		ShopItem item = new ShopItem();
		for (int i = 0; i < cart.size(); i++) {
			item = cart.get(i);
			user.buy(item);
			user.getCartItems().remove(item);
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

}