package MyProjects.Eshop.Controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import MyProjects.Eshop.App;
import MyProjects.Eshop.Model.ShopItem;
import MyProjects.Eshop.Model.User;
import MyProjects.Eshop.Service.ShopItemService;
import MyProjects.Eshop.Service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = App.class)
public class test_ShopItemController {
	
	@MockBean 
	ShopItemService shopItemService;
	
	@MockBean
	UserService userService;
	@MockBean
	ModelMap model;

	
	@Autowired
	MockMvc mockMvc;

	@Test
	@WithMockUser
	void test_goToAdd() throws Exception {
		User user = new User();
		when(userService.getCurrentUser()).thenReturn(user);
		mockMvc.perform(get("/add")).andExpect(status().isOk()).andExpect(view().name("add"));
	}
	

	
	@Test
	@WithMockUser
	void test_search() throws Exception {
		User user = new User();
		when(userService.getCurrentUser()).thenReturn(user);
		mockMvc.perform(get("/search")).andExpect(status().isOk()).andExpect(view().name("search"));
	}
	
	@Test
	@WithMockUser
	void test_searchItem_goodInputs() throws Exception {
		User user = new User();
		ShopItem item = new ShopItem();
		List<ShopItem> list = new ArrayList<>();
		list.add(item);
		when(userService.getCurrentUser()).thenReturn(user);
		when(shopItemService.findItem(model, "name","cat","10","20")).thenReturn(list);
		mockMvc.perform(post("/search")
				.param("searchTab", "name").param("category", "cat")
				.param("priceMin", "10").param("priceMax", "20"))
		.andExpect(status().isOk()).andExpect(view().name("search"));
	}
	

	
	@Test
	@WithMockUser
	void when_goToDetails() throws Exception {
		User user = new User();
		ShopItem item1 = new ShopItem();

		Optional<ShopItem> item = Optional.of(item1);
		when(shopItemService.findById(1)).thenReturn(item);
		Mockito.doReturn(user).when(userService).getCurrentUser();
		mockMvc.perform(get("/item/1")).andExpect(status().isOk())
		.andExpect(model().attribute("item", item.get()))
				.andExpect(view().name("details"));
	}
	
	@Test
	@WithMockUser
	void test_addToCart() throws Exception {
		User user = new User();
		ShopItem item1 = new ShopItem();

		Optional<ShopItem> item = Optional.of(item1);
		when(shopItemService.findById(10)).thenReturn(item);
		Mockito.doReturn(user).when(userService).getCurrentUser();
		mockMvc.perform(post("/addToCart").param("itemId","10")).andExpect(status().isOk())
		.andExpect(model().attribute("item", item.get()))
				.andExpect(view().name("details"));
	}
	
	@Test
	@WithMockUser
	void test_listByVendor() throws Exception {
		User user = new User();
		ShopItem item = new ShopItem();
		List<ShopItem> list = new ArrayList<>();
		list.add(item);
		when(userService.findById(10)).thenReturn(user);
		when(shopItemService.filterByVendor(user)).thenReturn(list);
		mockMvc.perform(post("/listByVendor").param("id","10")).andExpect(status().isOk())
		.andExpect(model().attribute("items", list))
		.andExpect(view().name("myItems"));
		
	}
	
	@Test
	@WithMockUser
	void test_listMyItems() throws Exception {
		User user = new User();
		ShopItem item = new ShopItem();
		List<ShopItem> list = new ArrayList<>();
		list.add(item);
		when(userService.getCurrentUser()).thenReturn(user);
		when(shopItemService.filterByVendor(user)).thenReturn(list);
		mockMvc.perform(get("/myItems")).andExpect(status().isOk())
		.andExpect(model().attribute("items", list))
		.andExpect(view().name("myItems"));
		
	}
	
	@Test
	@WithMockUser
	void test_myCart() throws Exception {
		User user = new User();
		ShopItem item = new ShopItem();
		Double totalPrice=20.0;
		List<ShopItem> myCart = new ArrayList<>();
		myCart.add(item);
		when(userService.getCurrentUser()).thenReturn(user);
		when(shopItemService.getCartItems(user)).thenReturn(myCart);
		when(shopItemService.getTotalPrice(myCart)).thenReturn(totalPrice);
		mockMvc.perform(get("/myCart")).andExpect(status().isOk())
		.andExpect(model().attribute("myCart", myCart))
		.andExpect(model().attribute("totalPrice", totalPrice))
		.andExpect(view().name("myCart"));
		
	}
	
	@Test
	@WithMockUser
	void test_buy_allItemsAvailable() throws Exception {
		List<ShopItem> noItem = new ArrayList<>();
		User user = new User();
		when(shopItemService.checkAvailable(user)).thenReturn(noItem);
		mockMvc.perform(get("/buy")).andExpect(status().isOk())
		.andExpect(view().name("profile"));
		
	}
	
	@Test
	@WithMockUser
	void test_buy_someItemsNotAvailable() throws Exception {
//		List<ShopItem> noItem = new ArrayList<>();
//		ShopItem item = new ShopItem();
//		User user = new User();
//item.setAmount(0);
//		noItem.add(item);
//		user.setCartItems(noItem);
//		when(shopItemService.checkAvailable(user)).thenReturn(noItem);
//		mockMvc.perform(get("/buy")).andExpect(status().isOk())
//		.andExpect(model().attribute("noItem", noItem))
//		.andExpect(model().attribute("message", "Some items are no more available. Please delete from cart:"))
//		.andExpect(view().name("myCart"));
		
	}
	
	@Test
	@WithMockUser
	void test_seeMyOrders() throws Exception {
		User user = new User();
		List<ShopItem> list = new ArrayList<>();
		when(shopItemService.getBuyed(user)).thenReturn(list);
		mockMvc.perform(post("/bought")).andExpect(status().isOk())
		.andExpect(model().attribute("bought", list))
		.andExpect(view().name("bought"));
		
	}
	
	@Test
	@WithMockUser
	void test_deleteFromCart() throws Exception {
		mockMvc.perform(post("/deleteFromCart").param("itemId","10")).andExpect(status().isOk())
		.andExpect(view().name("myCart"));
		
	}
	
}
