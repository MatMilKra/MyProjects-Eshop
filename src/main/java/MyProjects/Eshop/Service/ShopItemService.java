package MyProjects.Eshop.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import MyProjects.Eshop.Model.ShopItem;
import MyProjects.Eshop.Model.User;

public interface ShopItemService {

	void createNewItem(String name, String description, String category, double price, int amount,
			User owner, MultipartFile[] imageFile);

	void buy(User user);

	List<ShopItem> getCart(User user);

	List<ShopItem> getBuyed(User user);

	void deleteFromCart(User user, ShopItem item);

	Double getTotalPrice(List<ShopItem> myCart);

	List<ShopItem> checkAvailable(User user);

	String checkVendorAndAvailable(User user, ShopItem item);

	void addToCart(User user, ShopItem item);

	boolean parseDoublePossible(String string);

	boolean parseIntegerPossible(String string);

}
