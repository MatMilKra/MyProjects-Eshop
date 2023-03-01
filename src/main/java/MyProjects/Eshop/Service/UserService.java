package MyProjects.Eshop.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import MyProjects.Eshop.Model.User;

public interface UserService extends UserDetailsService{

	User getCurrentUser();
	
}
