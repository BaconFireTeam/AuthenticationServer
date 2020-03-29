package com.baconfire.authserver.dao.User;

import com.baconfire.authserver.entity.Role;
import com.baconfire.authserver.entity.User;
import com.baconfire.authserver.entity.UserRole;

import java.util.List;

public interface UserDao {
    User getUserByUserName(String username);

    UserRole getUserRole(int id);

    List<Role> getRolesByUserId(int userId);

    boolean checkUsername(String username);

    void registerUser(User user);
}
