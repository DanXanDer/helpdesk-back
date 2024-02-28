package portfolio.helpdesk.services;

import portfolio.helpdesk.models.User;

public interface IUserService extends ICRUD<User, Integer> {
    void findUserByUsernameOrEmail(String username, String email);
}
