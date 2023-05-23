package com.borja.cms.infrastructure.auth.output.repository;

import com.borja.cms.application.auth.dtos.SignUpDTO;
import com.borja.cms.application.auth.output.SignUpRepository;
import com.borja.cms.infrastructure.user.output.mongo.UserMongoRepository;
import com.borja.cms.infrastructure.user.output.mongo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class SignUpAdapter implements SignUpRepository {

    @Autowired
    UserMongoRepository userMongoRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public void save(SignUpDTO signUpDTO) {
        userMongoRepository.save(toUser(signUpDTO));
    }

    @Override
    public boolean existsUsername(String username) {
        return userMongoRepository.existsByUsername(username);
    }

    private User toUser(SignUpDTO signUpDTO) {
        User user = new User();
        user.setId(System.currentTimeMillis());
        user.setUsername(signUpDTO.username());
        user.setPasswordHash(encoder.encode(signUpDTO.password()));
        user.setCreatedAt(Date.from(Instant.now()));
        return user;
    }
}
