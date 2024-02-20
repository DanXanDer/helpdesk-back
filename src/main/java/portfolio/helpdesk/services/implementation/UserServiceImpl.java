package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.models.User;
import portfolio.helpdesk.repositories.IGenericRepo;
import portfolio.helpdesk.repositories.IUserRepo;
import portfolio.helpdesk.services.IUserService;

@RequiredArgsConstructor
@Service
public class UserServiceImpl extends CrudImpl<User, Integer> implements IUserService {

    private final IUserRepo userRepo;

    @Override
    protected IGenericRepo<User, Integer> getRepo() {
        return userRepo;
    }
}
