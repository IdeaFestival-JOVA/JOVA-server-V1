package com.jova.domain.auth.controller;

import com.jova.domain.auth.dto.request.GauthSignInRequest;
import com.jova.domain.auth.dto.request.KeySignInRequest;
import com.jova.domain.auth.dto.request.SignInRequest;
import com.jova.domain.auth.dto.request.SignUpRequest;
import com.jova.domain.auth.dto.response.TokenResponse;
import com.jova.domain.auth.entity.Auth;
import com.jova.domain.auth.service.*;
import com.jova.global.security.jwt.service.JwtProvider;
import com.jova.global.security.key.Entity.Key;
import com.jova.global.security.key.Repository.KeyRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@Tag(name = "Auth", description = "계정관련 API")
@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final SignInService signInService;
    private final ReissueTokenService reissueTokenService;
    private final LogoutService logoutService;
    private final AuthInfoService authInfoService;
    private final KeyRepository keyRepository;
    private final JwtProvider jwtProvider;
    private final SignUpService signUpService;


    @Operation(summary = "로그인", description = "GAuth를 이용한 로그인을 수행하는 API")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "로그인 성공"), @ApiResponse(responseCode = "400", description = "로그인 실패")})
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid GauthSignInRequest signInRequest) {
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

    @PostMapping("/signup")
    public ResponseEntity<Auth> signup(@RequestBody @Valid SignUpRequest signUpRequest) {
        Auth auth = signUpService.signUp(signUpRequest);
        return ResponseEntity.ok(auth);
    }

    @PostMapping("/key")
    public ResponseEntity<String> issueToken(@RequestParam String keyInput) {
        try {
            String token = jwtProvider.issueTokenIfKeyMatches(keyInput);
            return ResponseEntity.ok(token);
        } catch (IllegalArgumentException e) {
             return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}