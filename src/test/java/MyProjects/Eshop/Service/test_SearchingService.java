package MyProjects.Eshop.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;

import MyProjects.Eshop.Model.ShopItem;
import MyProjects.Eshop.Model.User;
import MyProjects.Eshop.Repository.ShopItemRepository;
import MyProjects.Eshop.Repository.UserRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class test_SearchingService {
	@InjectMocks
	private SearchingServiceImplementation searchingService;
	@InjectMocks
	private UserServiceImplementation userService;
	@MockBean
	private ShopItemRepository shopRepo;
	@MockBean
	private UserRepository userRepo;
	@MockBean
	ModelMap model;

	User user;
	String searchTab = "searchTab";
	String category = "Guitar";
	String priceMin = "10";
	String priceMax = "20";
	List<ShopItem> items;

	@BeforeEach
	public void newUser() {
		user = new User();
		items = new ArrayList<>();
	}

	@Test
	public void test_findAall() {
		searchingService.findAllItems();
		Mockito.verify(shopRepo).findAll();
	}

	@Test
	public void test_findById() {
		int id = 1;
		searchingService.findById(id);
		Mockito.verify(shopRepo).findById(id);

	}

	@Test
	public void test_filterByVendor() {
		searchingService.filterByVendor(user);
		Mockito.verify(shopRepo).findByVendor(user);
	}

	@Test
	public void test_findItem() {
		Double priceMinD = Double.parseDouble(priceMin);
		Double priceMaxD = Double.parseDouble(priceMax);

		ShopItem item = new ShopItem();

		items.add(item);
		when(shopRepo.findByNameContainsIgnoreCase(searchTab)).thenReturn(items);
		Mockito.when(shopRepo.findByDescriptionContainsIgnoreCase(searchTab)).thenReturn(items);
		Mockito.when(shopRepo.findByCategoryContainsIgnoreCase(category)).thenReturn(items);
		Mockito.when(shopRepo.findByPriceGreaterThanEqual(priceMinD)).thenReturn(items);
		Mockito.when(shopRepo.findByPriceLessThanEqual(priceMaxD)).thenReturn(items);
		List<ShopItem> find = searchingService.findItem(model, searchTab, category, priceMin, priceMax);
		assertEquals(items, find);
	}

	@Test
	public void test_findItem_badPriceMin() {
		String priceMin = "xyz";

		List<ShopItem> find = searchingService.findItem(model, searchTab, category, priceMin, priceMax);
		assertEquals(items, find);
	}

	@Test
	public void test_findItem_badPriceMax() {
		String priceMax = "xyz";

		List<ShopItem> find = searchingService.findItem(model, searchTab, category, priceMin, priceMax);
		assertEquals(items, find);
	}

}
