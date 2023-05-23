package com.borja.cms.application.auth;

import com.borja.cms.domain.auth.model.Reason;

import java.util.Optional;

public interface SignUpUseCase {

    Optional<Output> handle(Input input);

    record Input(String username, String password) {}

    record Output(Reason reason) {}

}
