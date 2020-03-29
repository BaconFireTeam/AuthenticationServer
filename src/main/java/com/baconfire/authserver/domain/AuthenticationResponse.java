package com.baconfire.authserver.domain;

import com.baconfire.authserver.domain.common.GenericServiceResponse;
import lombok.Builder;

import java.io.Serializable;

@Builder
public class AuthenticationResponse extends GenericServiceResponse implements Serializable {

    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}