package MyProjects.Eshop.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import MyProjects.Eshop.Model.ShopItem;
import MyProjects.Eshop.Model.User;

public interface ShopItemService {

	String createNewItem(ModelMap model, String name, String description, String category, String price, String amount, User owner, MultipartFile[] imageFile);


	List<ShopItem> findAllItems();

	Optional<ShopItem> findById(int id);

	List<ShopItem> filterByVendor(User user);

	List<ShopItem> findItem(ModelMap model, String searchTab, String category, String priceMin, String priceMax);


	void addToCart(ModelMap model, int id);


	void buy();

	List<ShopItem> getCart(User user);
	List<ShopItem> getBuyed(User user);

	List<ShopItem> checkAvailable();


	void deleteFromCart(int itemId);


	Double getTotalPrice(List<ShopItem> myCart);


	List<ShopItem> getCartItems(User user);

	

}
