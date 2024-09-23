package com.jova.domain.auth.controller;

import com.jova.domain.auth.dto.request.SignInRequest;
import com.jova.domain.auth.dto.response.TokenResponse;
import com.jova.domain.auth.service.SignInService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "계정관련 API")
@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    private final SignInService signInService;

    private AuthController(SignInService signInService) {
        this.signInService = signInService;
    }

    @Operation(summary = "로그인", description = "GAuth를 이용한 로그인을 수행하는 API")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "로그인 성공"), @ApiResponse(responseCode = "400", description = "로그인 실패")})
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid SignInRequest signInRequest) {
        TokenResponse tokenResponse = signInService.signIn(signInRequest);
        return ResponseEntity.ok(tokenResponse);
    }
}