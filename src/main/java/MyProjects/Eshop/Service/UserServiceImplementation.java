package MyProjects.Eshop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import MyProjects.Eshop.Model.User;
import MyProjects.Eshop.Repository.UserRepository;
import MyProjects.Eshop.Security.UserPrincipal;

public class UserServiceImplementation implements UserService {
	@Autowired
	private UserRepository repoUser;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String	usernameNew=username.toLowerCase();
		User user = repoUser.findByUsername(usernameNew).orElseThrow(() -> new UsernameNotFoundException(usernameNew));
		return new UserPrincipal(user);
	}
	
	@Override
	public User getCurrentUser() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
				
		return repoUser.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
	}
}
