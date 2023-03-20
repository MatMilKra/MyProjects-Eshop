package MyProjects.Eshop.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.ModelMap;

import MyProjects.Eshop.Model.MessageSend;
import MyProjects.Eshop.Model.User;

public interface UserService extends UserDetailsService
{

	User getCurrentUser();

	boolean checkIfUserLogged();

	Optional<User> getOptUser();

	User save(User user);

	Optional<User> findByUsername(String username);

	String activateCode(Integer userId, Integer filCode);


	User findById(int id);

	User updateUser(User user, User currUser);

	void checkInfo(ModelMap model);

	Boolean currentUserFrom(MessageSend message);

	List<User> findAll();

	void setRole(int id, String roleChoose);

	
}
