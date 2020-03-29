package com.baconfire.authserver.services.registration;

import com.baconfire.authserver.domain.registration.*;

public interface RegistrationService {
    GenerateTokenResponse generateEmployeeRegistrationToken(GenerateTokenRequest request);

    RegistrationTokenValidationResponse validateUserRegistrationToken(RegistrationTokenValidationRequest request);

    RegisterResponse registerEmployee(RegisterRequest request);
}
