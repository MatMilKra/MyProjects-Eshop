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

        if (searchTab.isEmpty()) {
            items = findAllItems();
        } else {
            List<ShopItem> nameMatches = shopItemRepo.findByNameContainsIgnoreCase(searchTab);
            List<ShopItem> descriptionMatches = shopItemRepo.findByDescriptionContainsIgnoreCase(searchTab);
            items.addAll(nameMatches);
            items.addAll(descriptionMatches);
            items = items.stream().distinct().collect(Collectors.toList());
        }

        if (!category.isEmpty()) {
            List<ShopItem> categoryMatches = shopItemRepo.findByCategoryContainsIgnoreCase(category);
            items = items.stream().distinct().filter(categoryMatches::contains).collect(Collectors.toList());
        }

        if (doubleMin > 0) {
            List<ShopItem> minPriceMatches = shopItemRepo.findByPriceGreaterThanEqual(doubleMin);
            items = items.stream().distinct().filter(minPriceMatches::contains).collect(Collectors.toList());
        }

        if (doubleMax > 0) {
            List<ShopItem> maxPriceMatches = shopItemRepo.findByPriceLessThanEqual(doubleMax);
            items = items.stream().distinct().filter(maxPriceMatches::contains).collect(Collectors.toList());
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
