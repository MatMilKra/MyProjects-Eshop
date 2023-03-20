package MyProjects.Eshop;

import java.sql.SQLException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import MyProjects.Eshop.Repository.MessageSendRepository;
import MyProjects.Eshop.Repository.UserRepository;
import MyProjects.Eshop.Service.MailServiceImplementation;


@SpringBootApplication
public class App {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		SpringApplication.run(App.class, args);
		
	}
}
