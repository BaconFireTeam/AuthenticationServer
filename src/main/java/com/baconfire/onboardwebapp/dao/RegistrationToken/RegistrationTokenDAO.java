package com.baconfire.onboardwebapp.dao.RegistrationToken;

public interface RegistrationTokenDAO {
    boolean checkToken(String token, String email);
}
