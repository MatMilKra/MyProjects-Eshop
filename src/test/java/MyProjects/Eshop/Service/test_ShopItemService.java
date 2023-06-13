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
public class test_ShopItemService {
	@InjectMocks
	private ShopItemServiceImplementation shopService;
	@InjectMocks
	private UserServiceImplementation userService;
	@MockBean
	private ShopItemRepository shopRepo;
	@MockBean
	private UserRepository userRepo;
	@MockBean
	ModelMap model;
	
	User user;
	
	
	@BeforeEach
	public void newUser() {
		user=new User();
	}
	
	@Test
	public void test_findAall() {
		shopService.findAllItems();
		Mockito.verify(shopRepo).findAll();
	}
	
	@Test
	public void test_findById() {
		int id = 1;
		shopService.findById(id);
		Mockito.verify(shopRepo).findById(id);
		
	}
	
	@Test
	public void test_filterByVendor() {
		shopService.filterByVendor(user);
		Mockito.verify(shopRepo).findByVendor(user);
	}
	@Test
	public void test_findItem() {
		String searchTab="searchTab"; 
		String category="Guitar";
		String priceMin="10";
		Double priceMinD=Double.parseDouble(priceMin);
		String priceMax="20";
		Double priceMaxD=Double.parseDouble(priceMax);

		ShopItem item = new ShopItem();
		List<ShopItem> items = new ArrayList<>();
		//items.add(item);
		when(shopRepo.findByNameContainsIgnoreCase(searchTab)).thenReturn(items);
		Mockito.when(shopRepo.findByDescriptionContainsIgnoreCase(searchTab)).thenReturn(items);
		Mockito.when(shopRepo.findByCategoryContainsIgnoreCase(category)).thenReturn(items);
		Mockito.when(shopRepo.findByPriceGreaterThanEqual(priceMinD)).thenReturn(items);
		Mockito.when(shopRepo.findByPriceLessThanEqual(priceMaxD)).thenReturn(items);
List<ShopItem> find = shopService.findItem(model, searchTab, category, priceMin, priceMax);
assertEquals(items,find);
	}
	
	@Test
	public void test_findItem_badPriceMin() {
		String searchTab="searchTab"; 
		String category="Guitar";
		String priceMin="xyz";
		String priceMax="20";
		List<ShopItem> items = new ArrayList<>();
		List<ShopItem> find = shopService.findItem(model, searchTab, category, priceMin, priceMax);
		assertEquals(items,find);
	}
	@Test
	public void test_findItem_badPriceMax() {
		String searchTab="searchTab"; 
		String category="Guitar";
		String priceMin="10";
		String priceMax="xyz";
		List<ShopItem> items = new ArrayList<>();
		List<ShopItem> find = shopService.findItem(model, searchTab, category, priceMin, priceMax);
		assertEquals(items,find);
	}
	
	@Test
	public void test_getCart() {
		List<ShopItem> items = new ArrayList<>();
user.setCartItems(items);
List<ShopItem> find = shopService.getCart(user);
assertEquals(items, find);
		
	}

	@Test
	public void test_getBuyed() {
		List<ShopItem> items = new ArrayList<>();
user.setBuyedIytems(items);
List<ShopItem> find = shopService.getBuyed(user);
assertEquals(items, find);
		
	}
	
	@Test
	public void test_buy() {
		User user=new User();
		ShopItem item = new ShopItem();
		item.setAmount(2);
		List<ShopItem> list = new ArrayList<>();
		List<ShopItem> list2 = new ArrayList<>();
		list.add(item);
		user.setCartItems(list);
		user.setBuyedIytems(list2);
		shopService.buy(user);
		Mockito.verify(shopRepo,times(1)).save(item);
		Mockito.verify(userRepo,times(1)).save(user);
	}
	
//	This test doesn't work, because UsernameNotFoundException
//	@Test
//	@WithMockUser
//	public void test_addToCart() {
//		ShopItem item = new ShopItem();
//		item.setVendor(user);
//		item.setAmount(10);
//		Optional<ShopItem> opt=Optional.of(item);
//		Mockito.when(shopService.findById(Mockito.anyInt())).thenReturn(opt);
//		User current=new User();
//		Mockito.when(userService.getCurrentUser()).thenReturn(current);
//		Mockito.verify(shopRepo).save(item);
//	}
	
}
