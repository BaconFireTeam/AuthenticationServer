package com.baconfire.authserver.domain.registration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationTokenValidationRequest {

    private String email;
    private String token;

}
