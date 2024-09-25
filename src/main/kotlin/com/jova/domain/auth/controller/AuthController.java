package com.jova.domain.auth.controller;

import com.jova.domain.auth.dto.request.SignInRequest;
import com.jova.domain.auth.dto.response.TokenResponse;
import com.jova.domain.auth.entity.Auth;
import com.jova.domain.auth.service.AuthInfoService;
import com.jova.domain.auth.service.LogoutService;
import com.jova.domain.auth.service.ReissueTokenService;
import com.jova.domain.auth.service.SignInService;
import com.jova.global.security.jwt.service.JwtProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "Auth", description = "계정관련 API")
@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private final SignInService signInService;
    @Autowired
    private final ReissueTokenService reissueTokenService;
    @Autowired
    private final LogoutService logoutService;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private AuthInfoService authInfoService;

    @Operation(summary = "로그인", description = "GAuth를 이용한 로그인을 수행하는 API")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "로그인 성공"), @ApiResponse(responseCode = "400", description = "로그인 실패")})
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid SignInRequest signInRequest) {
        TokenResponse tokenResponse = signInService.signIn(signInRequest);
        return ResponseEntity.ok(tokenResponse);
    }

    @Operation(summary = "토큰 재발급", description = "Refresh Token을 이용한 토큰 재발급을 수행하는 API")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "토큰 재발급 성공"), @ApiResponse(responseCode = "401", description = "인증 실패")})
    @PatchMapping("/reissue")
    public ResponseEntity<TokenResponse> reissue(@RequestHeader("Authorization") String refreshToken) {
        TokenResponse tokenResponse = reissueTokenService.reissueToken(refreshToken);
        return ResponseEntity.ok(tokenResponse);
    }

    @Operation(summary = "로그아웃", description = "로그아웃을 수행하는 API")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "로그아웃 성공"), @ApiResponse(responseCode = "401", description = "인증 실패")})
    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String accessToken) {
        logoutService.logout(accessToken);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "계정 정보확인", description = "계정 정보를 확인하는 API")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "계정 정보 확인 성공"), @ApiResponse(responseCode = "401", description = "인증 실패")})
    @GetMapping
    public Auth getAuthInfo(@RequestHeader("Authorization") String accessToken) {
        return authInfoService.getAuthInfo(accessToken);
    }
}