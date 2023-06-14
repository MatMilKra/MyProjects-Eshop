package MyProjects.Eshop.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.ui.ModelMap;

import MyProjects.Eshop.Model.ShopItem;
import MyProjects.Eshop.Model.User;

public interface SearchingService {
	List<ShopItem> findAllItems();

	Optional<ShopItem> findById(int id);

	List<ShopItem> filterByVendor(User user);

	List<ShopItem> findItem(ModelMap model, String searchTab, String category, String priceMin, String priceMax);
	
	ShopItem getItem(int id);
}
