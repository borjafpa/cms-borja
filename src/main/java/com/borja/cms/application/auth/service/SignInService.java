package com.borja.cms.application.auth.service;

import com.borja.cms.application.auth.SignInUseCase;
import com.borja.cms.infrastructure.security.service.UserDetailsImpl;
import com.borja.cms.infrastructure.security.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SignInService implements SignInUseCase {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public Output handle(Input input) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(input.username(), input.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        return new Output(input.username(), jwtCookie.toString());
    }

}
