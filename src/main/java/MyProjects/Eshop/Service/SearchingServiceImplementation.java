package MyProjects.Eshop.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import MyProjects.Eshop.Model.ShopItem;
import MyProjects.Eshop.Model.User;
import MyProjects.Eshop.Repository.ShopItemRepository;

@Service
public class SearchingServiceImplementation implements SearchingService {

	private ShopItemRepository shopItemRepo;

	@Autowired
	public SearchingServiceImplementation(ShopItemRepository shopItemRepo) {
		super();
		this.shopItemRepo = shopItemRepo;
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
	public List<ShopItem> findItem(ModelMap model, String searchTab, String category, String priceMin,
			String priceMax) {
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
	public ShopItem getItem(int id) {
		Optional<ShopItem> itemOp = findById(id);
		ShopItem item = new ShopItem();

		if (itemOp.isPresent()) {
			item = itemOp.get();
			return item;
		}

		return new ShopItem();
	}

}
