package MyProjects.Eshop.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.Authentication;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;

import MyProjects.Eshop.Model.MessageSend;
import MyProjects.Eshop.Model.Role;
import MyProjects.Eshop.Model.ShopItem;
import MyProjects.Eshop.Model.User;
import MyProjects.Eshop.Repository.RoleRepository;
import MyProjects.Eshop.Repository.UserRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class test_UserService {
	@InjectMocks
	private UserServiceImplementation userService;

	@MockBean
	private UserRepository userRepo;
	@MockBean
	private RoleRepository roleRepo;
	@Mock
	private RoleService roleService;
	@MockBean
	ModelMap model;

	@Test
	public void test_save() {
		User user = new User();
		userService.save(user);
		Mockito.verify(userRepo).save(user);
	}

	@Test
	public void test_updateUser() {
		User user1 = new User();
		User user2 = new User();
		userService.updateUser(user1, user2);
		Mockito.verify(userRepo, times(1)).save(user2);

	}

	@Test
	public void test_findById() {
		userService.findById(1);
		Mockito.verify(userRepo).findById(1);

	}

	@Test
	public void test_setRole() {
		Role role = new Role();
		User user = new User("a", "b", "c", "d", "e", role);
		Optional<User> userOp = Optional.of(user);

		Mockito.when(userRepo.findById(Mockito.anyInt())).thenReturn(userOp);
		Mockito.when(roleRepo.findByRolename(Mockito.anyString())).thenReturn(role);
		userService.setRole(1, "role");
		Mockito.verify(userRepo).save(user);
	}
@WithMockUser
	@Test
	public void test_loadUserByUsername_UserExists() {
		// Arrange
		String username = "testUser";
		User user = new User();
		user.setUsername(username);

		when(userRepo.findByUsername(username)).thenReturn(Optional.of(user));

		UserDetails userDetails = userService.loadUserByUsername(username);

		assertEquals(username, userDetails.getUsername());
		verify(userRepo, times(1)).findByUsername(username);
	}

	@Test
	public void test_loadUserByUsername_UserDoesNotExist() {
		String username = "nonexistentuser";

		when(userRepo.findByUsername(username)).thenReturn(Optional.empty());

		UsernameNotFoundException exception = org.junit.jupiter.api.Assertions
				.assertThrows(UsernameNotFoundException.class, () -> {
					userService.loadUserByUsername(username);
				});

		assertEquals(username, exception.getMessage());
		verify(userRepo, times(1)).findByUsername(username);
	}

	@Test
	public void test_findByUsername_UserExists() {
		String username = "testUser";
		User user = new User();
		user.setUsername(username);

		when(userRepo.findByUsername(username)).thenReturn(Optional.of(user));

		Optional<User> result = userService.findByUsername(username);

		assertTrue(result.isPresent());
		assertEquals(username, result.get().getUsername());
		verify(userRepo, times(1)).findByUsername(username);
	}

	@Test
	public void test_findByUsername_UserDoesNotExist() {
		String username = "nonExistentUser";

		when(userRepo.findByUsername(username)).thenReturn(Optional.empty());

		Optional<User> result = userService.findByUsername(username);

		assertFalse(result.isPresent());
		verify(userRepo, times(1)).findByUsername(username);
	}

	@Test
	public void test_getCurrentUser_UserExists() {
		String username = "testUser";
		User user = new User();
		user.setUsername(username);

		SecurityContext securityContext = mock(SecurityContext.class);
		Authentication authentication = mock(Authentication.class);

		when(securityContext.getAuthentication()).thenReturn(authentication);
		when(authentication.getName()).thenReturn(username);
		SecurityContextHolder.setContext(securityContext);

		when(userRepo.findByUsername(username)).thenReturn(Optional.of(user));

		User currentUser = userService.getCurrentUser();

		assertEquals(username, currentUser.getUsername());
		verify(userRepo, times(1)).findByUsername(username);
	}

	@Test
	public void test_getCurrentUser_UserDoesNotExist() {
		String username = "nonExistentUser";

		SecurityContext securityContext = mock(SecurityContext.class);
		Authentication authentication = mock(Authentication.class);

		when(securityContext.getAuthentication()).thenReturn(authentication);
		when(authentication.getName()).thenReturn(username);
		SecurityContextHolder.setContext(securityContext);

		when(userRepo.findByUsername(username)).thenReturn(Optional.empty());

		UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
			userService.getCurrentUser();
		});

		assertEquals(username, exception.getMessage());
		verify(userRepo, times(1)).findByUsername(username);
	}
	
    @Test
    public void test_optionalIsPresent_UserExists() {
        User user = new User();
        
        Optional<User> userOptional = Optional.of(user);

        boolean result = userService.optionalIsPresent(userOptional);

        assertTrue(result);
    }

    @Test
    public void test_optionalIsPresent_UserDoesNotExist() {
        Optional<User> userOptional = Optional.empty();

        boolean result = userService.optionalIsPresent(userOptional);

        assertFalse(result);
    }

    @Test
    public void test_getOptUser_UserExists() {
        String username = "testUser";
        User user = new User();
        user.setUsername(username);

        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);
        
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(username);

        SecurityContextHolder.setContext(securityContext);

        when(userRepo.findByUsername(username)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getOptUser();

        assertTrue(result.isPresent());
        assertEquals(username, result.get().getUsername());
        verify(userRepo, times(1)).findByUsername(username);
    }

    @Test
    public void test_getOptUser_UserDoesNotExist() {
        String username = "nonExistentUser";
        
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);
        
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(username);

        SecurityContextHolder.setContext(securityContext);

        when(userRepo.findByUsername(username)).thenReturn(Optional.empty());

        Optional<User> result = userService.getOptUser();

        assertFalse(result.isPresent());
        verify(userRepo, times(1)).findByUsername(username);
    }
    
    @WithMockUser
 @Test
    public void test_checkIfUserLogged_UserLogged() {
        User user = new User();
        Optional<User> userOptional = Optional.of(user);
        
        when(userService.getOptUser()).thenReturn(userOptional);

        boolean result = userService.checkIfUserLogged();

        assertTrue(result);
    }
@WithMockUser
    @Test
    public void test_checkIfUserLogged_UserNotLogged() {
        Optional<User> userOptional = Optional.empty();
        
        when(userService.getOptUser()).thenReturn(userOptional);

        boolean result = userService.checkIfUserLogged();

        assertFalse(result);
    }

    @Test
    public void test_activateCode_ActivationSuccessful() {
        Integer userId = 1;
        Integer activationCode = 12345;
        User user = new User();
        user.setActivateNum(activationCode);
        user.setActivated(false);
        
        Optional<User> userOptional = Optional.of(user);
        
        when(userRepo.findById(userId)).thenReturn(userOptional);

        String result = userService.activateCode(userId, activationCode);

        assertEquals("login", result);
        assertTrue(user.isActivated());
        verify(userRepo, times(1)).save(user);
    }

    @Test
    public void test_activateCode_ActivationFailed() {
        Integer userId = 1;
        Integer activationCode = 12345;
        User user = new User();
        user.setActivateNum(activationCode);
        user.setActivated(false);
        
        Optional<User> userOptional = Optional.of(user);
        
        when(userRepo.findById(userId)).thenReturn(userOptional);

        String result = userService.activateCode(userId, 54321);

        assertEquals("activate", result);
        assertFalse(user.isActivated());
        verify(userRepo, never()).save(user);
    }
    
    @WithMockUser
  @Test
    public void test_checkInfo_AdminUser() {
        User adminUser = new User();
        Role adminRole = new Role();
        adminRole.setRolename("Admin");
        adminUser.setRole(adminRole);
        
        Optional<User> userOptional = Optional.of(adminUser);

        ModelMap model = new ModelMap();

        when(userRepo.findByUsername(anyString())).thenReturn(userOptional);

        userService.checkInfo(model);

        assertTrue((Boolean) model.get("checkInfo"));
    }
@WithMockUser
    @Test
    public void test_checkInfo_NonAdminUser() {
        User regularUser = new User();
        Role regularRole = new Role();
        regularRole.setRolename("Customer");
        regularUser.setRole(regularRole);
        
        Optional<User> userOptional = Optional.of(regularUser);

        ModelMap model = new ModelMap();

        when(userRepo.findByUsername(anyString())).thenReturn(userOptional);

        userService.checkInfo(model);

        assertFalse((Boolean) model.get("checkInfo"));
    }

@WithMockUser
  @Test
    public void test_currentUserFrom_CurrentUserIsMessageSender() {
        User currentUser = new User();
        currentUser.setUsername("currentUser");

        User messageSender = new User();
        messageSender.setUsername("messageSender");

        MessageSend message = new MessageSend();
        message.setFrom(currentUser);

        when(userRepo.findByUsername(anyString())).thenReturn(Optional.of(currentUser));

        boolean result = userService.currentUserFrom(message);

        assertTrue(result);
    }
@WithMockUser
    @Test
    public void test_currentUserFrom_CurrentUserIsNotMessageSender() {
        User currentUser = new User();
        currentUser.setUsername("currentUser");

        User messageSender = new User();
        messageSender.setUsername("messageSender");

        MessageSend message = new MessageSend();
        message.setFrom(messageSender);

        when(userRepo.findByUsername(anyString())).thenReturn(Optional.of(currentUser));

        boolean result = userService.currentUserFrom(message);

        assertFalse(result);
    }

    @Test
    public void test_findAll() {
        User user1 = new User();
        User user2 = new User();
        List<User> userList = Arrays.asList(user1, user2);

        when(userRepo.findAll()).thenReturn(userList);

        List<User> result = userService.findAll();

        assertEquals(2, result.size());
        verify(userRepo, times(1)).findAll();
    }
    
    @WithMockUser
 @Test
    public void test_populateUser_UserLogged() {
        User user = new User();
        user.setUsername("testUser");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setPhoneNumber("123-456-7890");
        Optional<User> optUser = Optional.of(user);
//        when(userService.checkIfUserLogged()).thenReturn(true);

        when(userRepo.findByUsername(anyString())).thenReturn(optUser);

        ModelMap model = new ModelMap();

        userService.populateUser(model);

        assertEquals("testUser", model.get("userUsername"));
        assertEquals("John", model.get("userFirstName"));
        assertEquals("Doe", model.get("userLastName"));
        assertEquals("john.doe@example.com", model.get("userEmail"));
        assertEquals("123-456-7890", model.get("userPhoneNumber"));
    }

    @WithMockUser
@Test
    public void test_populateUser_UserNotLogged() {
        User user = new User();

        Optional<User> optUser = Optional.of(user);

      when(userRepo.findByUsername(anyString())).thenReturn(optUser);
//        when(userService.checkIfUserLogged()).thenReturn(false);

        ModelMap model = new ModelMap();

        userService.populateUser(model);

        assertNull(model.get("userUsername"));
        assertNull(model.get("userFirstName"));
        assertNull(model.get("userLastName"));
        assertNull(model.get("userEmail"));
        assertNull(model.get("userPhoneNumber"));
    }

    @Test
    public void test_randomNumber_find() {
    	int howManyTests = 100;
    	
    	for(int i =0;i < howManyTests;i++) {
        Integer actNum = userService.randomNumber_find();

        assertTrue(actNum >= 100000 && actNum <= 999999);
    	}
    }
    
    @Test
    public void test_registerNew_SuccessfulRegistration() {
        User user = new User();
        user.setUsername("testUser");
        user.setRole(new Role());
        String newPassword = "newPassword";

        when(roleService.findByRoleName("Customer")).thenReturn(new Role());

//        when(userService.randomNumber_find()).thenReturn(12345);

        String result = userService.registerNew(user, newPassword);

        assertEquals("activate", result);
        verify(userRepo, times(1)).save(user);
    }

    @Test
    public void test_alreadyExist_UserExists() {
        User user = new User();
        user.setUsername("testUser");

        when(userRepo.findByUsername("testUser")).thenReturn(Optional.of(user));

        boolean result = userService.alreadyExist(user);

        assertTrue(result);
    }

    @Test
    public void test_alreadyExist_UserDoesNotExist() {
        when(userRepo.findByUsername("nonExistentUser")).thenReturn(Optional.empty());
        
        boolean result = userService.alreadyExist(new User());

        assertFalse(result);
    }

    @Test
    public void test_checkPassword_PasswordMatches() {
        User user = new User();
        user.setPassword("password");

        boolean result = userService.checkPassword(user, "password");

        assertTrue(result);
    }

    @WithMockUser
 @Test
    public void test_checkPassword_PasswordDoesNotMatch() {
        User user = new User();
        user.setPassword("password");

        boolean result = userService.checkPassword(user, "wrongPassword");

        assertFalse(result);
    }
}
