package com.borja.cms.application.auth.service;

import com.borja.cms.application.auth.dtos.SignUpDTO;
import com.borja.cms.application.auth.SignUpUseCase;
import com.borja.cms.application.auth.output.SignUpRepository;
import com.borja.cms.domain.auth.model.Reason;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SignUpService implements SignUpUseCase {

    @Autowired
    SignUpRepository signUpRepository;

    @Override
    public Optional<Output> handle(Input input) {
        if (signUpRepository.existsUsername(input.username())) {
            return Optional.of(new Output(Reason.ALREADY_EXISTENT_USERNAME));
        }

        signUpRepository.save(toSignUpDTO(input));

        return Optional.empty();
    }

    private SignUpDTO toSignUpDTO(Input input) {
        return new SignUpDTO(input.username(), input.password());
    }
}
