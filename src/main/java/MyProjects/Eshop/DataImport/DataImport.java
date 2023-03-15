package MyProjects.Eshop.DataImport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import MyProjects.Eshop.Model.Role;
import MyProjects.Eshop.Model.User;
import MyProjects.Eshop.Repository.UserRepository;

@Component
public class DataImport implements ApplicationRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		if (!userRepository.findByUsername("admin").isPresent()) {
			Role roleAdmin = new Role("Admin");
			Role roleCustomer = new Role("Customer");

			User admin = new User("admin", encoder.encode("123"), roleAdmin);
			admin.setActivated(true);
			admin.setEmail("karp.kochany@wp.pl");
			userRepository.save(admin);

			User customer = new User("customer", encoder.encode("123"), roleCustomer);
			customer.setActivated(true);
			userRepository.save(customer);
		}
	}
}
