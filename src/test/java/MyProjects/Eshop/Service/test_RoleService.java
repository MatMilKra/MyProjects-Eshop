package MyProjects.Eshop.Service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import MyProjects.Eshop.Model.Role;
import MyProjects.Eshop.Repository.RoleRepository;

@ExtendWith(MockitoExtension.class)
public class test_RoleService {
	@InjectMocks
	RoleServiceImplementation roleServ;

	@Mock
	RoleRepository roleRepo;

	@Test
	public void test_findByRoleName() {
		roleServ.findByRoleName("role");
		verify(roleRepo).findByRolename("role");

	}

	@Test
	public void test_addRole() {
		Role role = new Role();
		roleServ.addRole(role);
		verify(roleRepo).save(role);

	}
}
