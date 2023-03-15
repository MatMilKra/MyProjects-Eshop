package MyProjects.Eshop.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import MyProjects.Eshop.Model.ShopItem;
import MyProjects.Eshop.Model.User;

@Repository
public interface ShopItemRepository extends JpaRepository<ShopItem,Integer>{

	List<ShopItem> findByNameContainsIgnoreCase(String searchTab);

	List<ShopItem> findByVendor(User user);

	List<ShopItem> findByDescriptionContainsIgnoreCase(String searchTab);
	

	List<ShopItem> findByCategoryContainsIgnoreCase(String category);

	List<ShopItem> findByPriceGreaterThanEqual(Double priceMin);

	List<ShopItem> findByPriceLessThanEqual(Double priceMax);
}
