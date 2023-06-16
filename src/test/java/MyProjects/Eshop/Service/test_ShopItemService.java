package MyProjects.Eshop.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.io.File;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;

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
	ShopItem item;
	List<ShopItem> cart;
	
	@BeforeEach
	public void newUser() {
		user=new User();
		item = new ShopItem();
		cart = new ArrayList<>();
		cart.add(item);
		user.setCartItems(cart);
	}
	

	
	@Test
	public void test_getCart() {
//user.setCartItems(cart);
List<ShopItem> find = shopService.getCart(user);
assertEquals(cart, find);
		
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
		item.setAmount(2);
		List<ShopItem> list2 = new ArrayList<>();
//		cart.add(item);
//		user.setCartItems(cart);
		user.setBuyedIytems(list2);
		shopService.buy(user);
		Mockito.verify(shopRepo,times(1)).save(item);
		Mockito.verify(userRepo,times(1)).save(user);
	}
	
	@Test
	public void test_checkAvailable_amount1_ReturnEmptyList() {
		item.setAmount(1);
//		cart.add(item);
//		user.setCartItems(cart);
		List<ShopItem> check = new ArrayList<>();
		List<ShopItem> actual = shopService.checkAvailable(user);
		assertEquals(check, actual);
	}
	
	@Test
	public void test_checkAvailable_amount0_ReturnNonemptyList() {
		item.setAmount(0);
//		cart.add(item);
//		user.setCartItems(cart);
		List<ShopItem> actual = shopService.checkAvailable(user);
		assertEquals(cart, actual);
	}
	
@Test
public void test_getTotalPrice(){
	ShopItem item1 = new ShopItem();
	item.setPrice(10.0);
	item1.setPrice(20.0);
//	cart.add(item);
	cart.add(item1);
	Double  actual = shopService.getTotalPrice(cart);
	assertEquals(30.0, actual);
}

@Test
public void test_checkVendorAndAvailable_returnAccept() {
	User vendor = new User();
	item.setVendor(vendor);
	item.setAmount(1);
	String actual = shopService.checkVendorAndAvailable(user, item);
	assertEquals("accept", actual);
}

@Test
public void test_checkVendorAndAvailable_returnAmount() {
	User vendor = new User();
	item.setVendor(vendor);
	item.setAmount(0);
	String actual = shopService.checkVendorAndAvailable(user, item);
	assertEquals("amount", actual);
}

@Test
public void test_checkVendorAndAvailable_returnVendor() {
	item.setVendor(user);
	item.setAmount(0);  // it's 0 because we want to be sure, that method will return "vendor"
	String actual = shopService.checkVendorAndAvailable(user, item);
	assertEquals("vendor", actual);
}



@Test
public void addToCart() {
	shopService.addToCart(user, item);
	Mockito.verify(userRepo).save(user);
	
}

@Test
public void test_deleteFromCart() {
	shopService.deleteFromCart(user, item);
	
	Mockito.verify(userRepo).save(user);

}

@Test
public void test_parseDoublePossible_returnFalse() {
	boolean test = shopService.parseDoublePossible("abc");
	assertEquals(false, test);
	
}

@Test
public void test_parseDoublePossible_returnTrue() {
	boolean test = shopService.parseDoublePossible("10.7");
	assertEquals(true, test);
	
}
@Test
public void test_parseIntegerossible_returnFalse() {
	boolean test = shopService.parseIntegerPossible("abc");
	assertEquals(false, test);
	
}

@Test
public void test_parseIntegerPossible_returnTrue() {
	boolean test = shopService.parseIntegerPossible("10");
	assertEquals(true, test);
	
}

	
}
