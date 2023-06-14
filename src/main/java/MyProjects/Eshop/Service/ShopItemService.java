package MyProjects.Eshop.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import MyProjects.Eshop.Model.ShopItem;
import MyProjects.Eshop.Model.User;

public interface ShopItemService {

	String createNewItem(ModelMap model, String name, String description, String category, String price, String amount,
			User owner, MultipartFile[] imageFile);

	void buy(User user);

	List<ShopItem> getCart(User user);

	List<ShopItem> getBuyed(User user);

	void deleteFromCart(ShopItem item);

	Double getTotalPrice(List<ShopItem> myCart);

	List<ShopItem> checkAvailable(User user);

	void addToCart(ModelMap model, User user, ShopItem item);

}
