package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.exceptions.ModelAlreadyExistsException;
import portfolio.helpdesk.models.User;
import portfolio.helpdesk.repositories.IGenericRepo;
import portfolio.helpdesk.repositories.IUserRepo;
import portfolio.helpdesk.services.IUserService;

@RequiredArgsConstructor
@Service
public class UserServiceImpl extends CrudImpl<User, Integer> implements IUserService {

    private final IUserRepo userRepo;

    @Override
    protected IUserRepo getRepo() {
        return userRepo;
    }

    @Override
    public void findUserByUsernameOrEmail(String username, String email) {
        getRepo().findUserByUsernameOrEmail(username, email).ifPresent(user -> {
            throw new ModelAlreadyExistsException("User with this username or email already exists.");
        });
    }
}
