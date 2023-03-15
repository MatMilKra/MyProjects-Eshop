package MyProjects.Eshop.Service;

import MyProjects.Eshop.Model.Role;

public interface RoleService {

	Role findByRoleName(String string);

	void addRole(Role role);

}
