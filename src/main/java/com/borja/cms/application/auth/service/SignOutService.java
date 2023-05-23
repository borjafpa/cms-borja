package com.borja.cms.application.auth.service;

import com.borja.cms.application.auth.SignOutUseCase;
import com.borja.cms.infrastructure.security.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class SignOutService implements SignOutUseCase {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public Output handle() {
        ResponseCookie jwtCookie = jwtUtils.getCleanJwtCookie();

        return new Output(jwtCookie.toString());
    }

}
