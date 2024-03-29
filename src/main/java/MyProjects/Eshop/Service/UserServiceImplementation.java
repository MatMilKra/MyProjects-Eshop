package MyProjects.Eshop.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import MyProjects.Eshop.Model.MessageSend;
import MyProjects.Eshop.Model.Role;
import MyProjects.Eshop.Model.User;
import MyProjects.Eshop.Repository.RoleRepository;
import MyProjects.Eshop.Repository.UserRepository;
import MyProjects.Eshop.Security.UserPrincipal;

@Service
public class UserServiceImplementation implements UserService {
	@Autowired
	private UserRepository repoUser;
	@Autowired
	private RoleRepository repoRole;

	@Autowired
	private RoleService roleService;

	@Autowired
	public UserServiceImplementation(UserRepository repoUser, RoleRepository repoRole,
			RoleService roleService) {
		super();
		this.repoUser = repoUser;
		this.repoRole = repoRole;
		this.roleService = roleService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String usernameNew = username.toLowerCase();
		User user = repoUser.findByUsername(usernameNew).orElseThrow(() -> new UsernameNotFoundException(usernameNew));
		return new UserPrincipal(user);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return repoUser.findByUsername(username);
	}

	@Override
	public User getCurrentUser() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		return repoUser.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
	}

	@Override
	public User save(User user) {
		return repoUser.save(user);
	}

	@Override
	public boolean optionalIsPresent(Optional<User> user) {
		return user.isPresent();
	}

	@Override
	public Optional<User> getOptUser() {
		return repoUser.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

	}

	@Override
	public boolean checkIfUserLogged() {
		Optional<User> user = getOptUser();

		if (optionalIsPresent(user))
			return true;

		return false;

	}

	@Override
	public String activateCode(Integer userId, Integer filCode) {
		Optional<User> user = repoUser.findById(userId);
		if (optionalIsPresent(user))
		{
			User user2 = user.get();
			if (user2.getActivateNum().equals(filCode)) {
				user2.setActivated(true);
				repoUser.save(user2);
				return "login";
			}
		}
		return "activate";
	}

	@Override
	public User updateUser(User user, User currUser) {

		currUser.setFirstName(user.getFirstName());
		currUser.setLastName(user.getLastName());
		currUser.setPhoneNumber(user.getPhoneNumber());
		currUser.setEmail(user.getEmail());
		repoUser.save(currUser);
		return currUser;
	}

	@Override
	public User findById(int id) {
		return repoUser.findById(id).orElse(new User());
	}

	@Override
	public void checkInfo(ModelMap model) {
		Optional<User> user = getOptUser();
		boolean checkInfo = false;
		if (optionalIsPresent(user))

			if (user.get().getRole().getRolename().equals("Admin"))
				checkInfo = true;
		model.addAttribute("checkInfo", checkInfo);
	}

	@Override
	public Boolean currentUserFrom(MessageSend message) {
		User user = getCurrentUser();
		if (message.getFrom() == user)
			return true;
		return false;
	}

	@Override
	public List<User> findAll() {
		return repoUser.findAll();
	}

	public void setRole(int id, String roleChoose) {
		if (!roleChoose.isEmpty()) {
			User user = findById(id);
			Role role = repoRole.findByRolename(roleChoose);
			user.setRole(role);
			repoUser.save(user);
		}
	}



	@Override
	public void populateUser(ModelMap model) {
		if (checkIfUserLogged()) {
			User user = getCurrentUser();
			model.addAttribute("userUsername", user.getUsername());
			model.addAttribute("userFirstName", user.getFirstName());
			model.addAttribute("userLastName", user.getLastName());
			model.addAttribute("userEmail", user.getEmail());
			model.addAttribute("userPhoneNumber", user.getPhoneNumber());
		}

	}

	@Override
	public Integer randomNumber_find() {
		Integer actNum = (int) (Math.random() * 900000 + 100000);
		return actNum;
	}

	@Override
	public String registerNew(User user, String newPass) {


		user.setRole(roleService.findByRoleName("Customer"));
		user.setPassword(newPass);
		user.setActivateNum(randomNumber_find());
		save(user);

		
		return "activate";
	}

	@Override
	public boolean alreadyExist(User user) {
		Optional<User> userFromDatabase = findByUsername(user.getUsername());
		if (optionalIsPresent(userFromDatabase))
			return true;
		return false;
	}

	@Override
	public boolean checkPassword(User user, String passwordConfirmed) {
		if (user.getPassword().equals(passwordConfirmed)) 
			return true;
		return false;
	}


}
