package com.borja.cms.application.auth.output;

import com.borja.cms.application.auth.dtos.SignUpDTO;

public interface SignUpRepository {
    void save(SignUpDTO signUpDTO);

    boolean existsUsername(String username);
}
