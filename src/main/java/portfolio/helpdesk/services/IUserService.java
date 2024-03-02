package portfolio.helpdesk.services;

import portfolio.helpdesk.models.UserData;

public interface IUserService extends ICRUD<UserData, Integer> {
    void findByUsernameOrEmail(String username, String email);
}
