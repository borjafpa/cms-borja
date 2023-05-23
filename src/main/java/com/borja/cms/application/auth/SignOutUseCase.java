package com.borja.cms.application.auth;

public interface SignOutUseCase {

    Output handle();

    record Output(String cookie) {}

}
