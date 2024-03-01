package portfolio.helpdesk.services;

import portfolio.helpdesk.models.Role;

import java.util.Set;

public interface IRoleService extends ICRUD<Role, Integer> {
    Set<Role> findRolesNoAdmin();
}
