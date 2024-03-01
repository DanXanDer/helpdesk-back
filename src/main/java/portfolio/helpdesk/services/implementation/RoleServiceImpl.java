package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.models.Role;
import portfolio.helpdesk.repositories.IRoleRepo;
import portfolio.helpdesk.services.IRoleService;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl extends CrudImpl<Role, Integer> implements IRoleService {
    private final IRoleRepo roleRepo;

    @Override
    protected IRoleRepo getRepo() {
        return roleRepo;
    }

    public Set<Role> findRolesNoAdmin() {
        return getRepo().findRolesNoAdmin();
    }
}
