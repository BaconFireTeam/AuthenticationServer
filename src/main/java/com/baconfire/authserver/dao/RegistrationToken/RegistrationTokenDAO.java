package com.baconfire.authserver.dao.RegistrationToken;

import com.baconfire.authserver.entity.RegistrationToken;

public interface RegistrationTokenDAO {
    RegistrationToken validateTokenAndEmail(String token, String email);

    boolean isTokenExisted(String token);

    RegistrationToken save(RegistrationToken token);
}
