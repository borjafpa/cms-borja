package com.borja.cms.infrastructure.auth.input;

import com.borja.cms.application.auth.SignInUseCase;
import com.borja.cms.application.auth.SignOutUseCase;
import com.borja.cms.application.auth.SignUpUseCase;
import com.borja.cms.infrastructure.auth.input.dtos.SignInRequest;
import com.borja.cms.infrastructure.auth.input.dtos.SignInResponse;
import com.borja.cms.infrastructure.auth.input.dtos.SignUpRequest;
import com.borja.cms.infrastructure.auth.input.dtos.SignUpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = AuthController.BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AuthController {

    public static final String BASE_URL = "/auth/v1/";

    @Autowired
    SignUpUseCase signUpUseCase;

    @Autowired
    SignInUseCase signInUseCase;

    @Autowired
    SignOutUseCase signOutUseCase;

    @PostMapping("sign-up")
    public ResponseEntity<SignUpResponse> signUpUser(@RequestBody SignUpRequest request) {
        Optional<SignUpUseCase.Output> outputOpt = signUpUseCase.handle(new SignUpUseCase.Input(request.username(),request.password()));

        if (outputOpt.isEmpty()) {
            return ResponseEntity.ok().build();
        }

        SignUpUseCase.Output output = outputOpt.get();

        return ResponseEntity.badRequest()
                .body(new SignUpResponse(
                        output.reason().getCode(),
                        output.reason().getMessage()));
    }

    @PostMapping("sign-in")
    public ResponseEntity<?> signInUser(@RequestBody SignInRequest request) {
        SignInUseCase.Output output = signInUseCase.handle(new SignInUseCase.Input(request.username(), request.password()));

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, output.cookie())
                .body(new SignInResponse(output.username()));
    }


    @PostMapping("sign-out")
    public ResponseEntity<?> logoutUser() {
        SignOutUseCase.Output output = signOutUseCase.handle();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, output.cookie()).build();
    }

}
