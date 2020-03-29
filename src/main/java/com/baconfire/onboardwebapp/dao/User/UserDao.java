package com.baconfire.onboardwebapp.dao.User;

import com.baconfire.onboardwebapp.domain.Role;
import com.baconfire.onboardwebapp.domain.User;
import com.baconfire.onboardwebapp.domain.UserRole;

public interface UserDao {
    User login(String username, String password);

    UserRole getUserRole(int id);

    Role getRole(int id);

    boolean checkUsername(String username);

    void registerUser(User user);
}
