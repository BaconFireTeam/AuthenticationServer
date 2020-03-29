package com.baconfire.authserver.services.registration.impl;

import com.baconfire.authserver.dao.Person.PersonDAO;
import com.baconfire.authserver.dao.RegistrationToken.RegistrationTokenDAO;
import com.baconfire.authserver.dao.User.UserDao;
import com.baconfire.authserver.domain.common.GenericServiceResponse;
import com.baconfire.authserver.domain.common.ServiceStatus;
import com.baconfire.authserver.domain.registration.*;
import com.baconfire.authserver.entity.Person;
import com.baconfire.authserver.entity.RegistrationToken;
import com.baconfire.authserver.entity.User;
import com.baconfire.authserver.services.registration.RegistrationService;
import com.baconfire.authserver.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private RegistrationTokenDAO registrationTokenDAO;

    private UserDao userDao;

    private PersonDAO personDAO;

    @Autowired
    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Autowired
    public void setRegistrationTokenDAO(RegistrationTokenDAO registrationTokenDAO) {
        this.registrationTokenDAO = registrationTokenDAO;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public GenerateTokenResponse generateEmployeeRegistrationToken(GenerateTokenRequest request) {

        GenerateTokenResponse response = new GenerateTokenResponse();

        String token = TokenUtil.generateToken();
        while (registrationTokenDAO.isTokenExisted(token)) {
            token = TokenUtil.generateToken();
        }

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        RegistrationToken registrationToken = RegistrationToken.builder()
                .token(token)
                .email(request.getEmail())
                .createdBy(userDetails.getUsername())
                .validDuration("3600000")
                .build();

        RegistrationToken savedRegistrationToken = registrationTokenDAO.save(registrationToken);

        if (savedRegistrationToken != null) {
            prepareResponse(response, true, null, null);
            return response;
        }

        prepareResponse(response, false, "99", "Failed to generate registration token");
        return response;
    }

    @Override
    @Transactional
    public RegistrationTokenValidationResponse validateUserRegistrationToken(RegistrationTokenValidationRequest request) {

        RegistrationTokenValidationResponse response = new RegistrationTokenValidationResponse();

        RegistrationToken registrationToken = registrationTokenDAO.validateTokenAndEmail(request.getToken(), request.getEmail());

        if (registrationToken == null) {
            prepareResponse(response, false, "99", "Invalid Token or Email");
            return response;
        }

        prepareResponse(response, true, null, null);
        return response;
    }

    @Override
    @Transactional
    public RegisterResponse registerEmployee(RegisterRequest request) {

        RegisterResponse registerResponse = new RegisterResponse();

        Person person = Person.builder()
                .email(request.getEmail())
                .firstname("-")
                .lastname("-")
                .cellphone("-")
                .build();

        Person savedPerson = personDAO.savePerson(person);

        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .active("Y")
                .createDate(TokenUtil.getFormattedDate(LocalDateTime.now()))
                .person(savedPerson)
                .email(request.getEmail())
                .modificationDate(TokenUtil.getFormattedDate(LocalDateTime.now()))
                .build();

        userDao.registerUser(user);

        prepareResponse(registerResponse, true, null, null);
        return registerResponse;
    }

    private void prepareResponse(GenericServiceResponse response, boolean success, String statusCode, String errorMessage) {
        ServiceStatus serviceStatus = ServiceStatus.builder()
                .statusCode(success ? "00" : statusCode)
                .errorMessage(success ? "SUCCESS" : statusCode)
                .success(success)
                .build();

        response.setServiceStatus(serviceStatus);
    }


}
