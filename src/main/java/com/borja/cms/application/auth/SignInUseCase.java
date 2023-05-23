package com.borja.cms.application.auth;

import java.util.Date;
import java.util.Optional;

public interface SignInUseCase {

    Output handle(Input input);

    record Input(String username, String password) {}

    record Output(String username, String cookie) {}

}
