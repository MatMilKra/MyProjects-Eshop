package MyProjects.Eshop.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import MyProjects.Eshop.Model.User;
import MyProjects.Eshop.Repository.UserRepository;
import MyProjects.Eshop.Security.UserPrincipal;

@Service
public class UserServiceImplementation implements UserService {
	@Autowired
	private UserRepository repoUser;

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
	public Optional<User> getOptUser() {
		return repoUser.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

	}

	@Override
	public boolean checkIfUserLogged() {
		Optional<User> user = getOptUser();

		if (user.isPresent())
			return true;

		return false;

	}

	@Override
	public String activateCode(Integer userId, Integer filCode) {
		Optional<User> user = repoUser.findById(userId);
		if (user.isPresent()) {

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
		if (user.isPresent())
			if (user.get().getRole().getRolename().equals("Admin"))
				checkInfo = true;
		model.addAttribute("checkInfo", checkInfo);
	}
}
