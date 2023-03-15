package MyProjects.Eshop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MyProjects.Eshop.Model.Role;
import MyProjects.Eshop.Repository.RoleRepository;

@Service
public class RoleServiceImplementation implements RoleService {

	@Autowired
	private RoleRepository repo;

	@Override
	public Role findByRoleName(String rolename) {
		return repo.findByRolename(rolename);
	}

	@Override
	public void addRole(Role role) {
		repo.save(role);
	}

}
