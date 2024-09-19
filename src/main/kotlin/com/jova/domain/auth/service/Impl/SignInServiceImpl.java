package com.jova.domain.auth.service.Impl;

import com.jova.domain.auth.dto.request.SignInRequest;
import com.jova.domain.auth.dto.response.tokenResponse;
import com.jova.domain.auth.entity.Auth;
import com.jova.domain.auth.enums.Authority;
import com.jova.domain.auth.exception.AuthNotFoundException;
import com.jova.domain.auth.repository.AuthRepository;
import com.jova.domain.auth.service.SignInService;
import com.jova.domain.auth.vo.StudentNum;
import gauth.GAuth;
import gauth.GAuthToken;
import gauth.GAuthUserInfo;
import gauth.exception.GAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jova.global.security.jwt.JwtProvider;
import com.jova.domain.auth.entity.RefreshToken;

@Service
@Transactional
public class SignInServiceImpl implements SignInService {
    private final GAuth gAuth;
    private final AuthRepository authRepository;
    private final RefreshRepository refreshRepository;
    private final JwtProvider jwtProvider;

    @Value("${GAuth-CLIENT-ID}")
    private String clientId;

    @Value("${GAuth-CLIENT-SECRET}")
    private String clientSecret;

    @Value("${GAuth-REDIRECT-URI}")
    private String redirectUri;

    @Autowired
    public SignInServiceImpl(GAuth gAuth,
                             AuthRepository authRepository,
                             RefreshRepository refreshRepository,
                             JwtProvider jwtProvider) {
        this.gAuth = gAuth;
        this.authRepository = authRepository;
        this.refreshRepository = refreshRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public tokenResponse signIn(SignInRequest request) {
        try {
            GAuthToken gAuthToken = gAuth.generateToken(
                    request.getCode(), clientId, clientSecret, redirectUri
            );
            String accessToken = gAuthToken.getAccessToken();
            GAuthUserInfo userInfo = gAuth.getUserInfo(accessToken);
            Auth auth = authRepository.findByEmail(userInfo.getEmail());
            if (auth == null) {
                auth = saveAuth(userInfo);
                if (auth == null) {
                    throw new AuthNotFoundException("Auth 생성 실패");
                }
            }
            tokenResponse tokenResponse = jwtProvider.generateTokenDto(auth.getId());
            if (tokenResponse == null) {
                throw new AuthNotFoundException("토큰 생성 실패");
            }
            saveRefreshToken(tokenResponse, auth);
            return tokenResponse;
        } catch (GAuthException e) {
            throw new GAuthException(e.getCode());
        }
    }

    private Auth saveAuth(GAuthUserInfo gAuthUserInfo) {
        Auth auth = null;
        switch (gAuthUserInfo.getRole()) {
            case "ROLE_STUDENT":
                auth = saveStudent(gAuthUserInfo);
                break;
            case "ROLE_TEACHER":
                auth = saveTeacher(gAuthUserInfo);
                break;
            default:
                break;
        }
        return auth;
    }

    private Auth saveStudent(GAuthUserInfo gAuthUserInfo) {
        Auth auth = new Auth();
        auth.setEmail(gAuthUserInfo.getEmail());
        auth.setName(gAuthUserInfo.getName());
        auth.setStudentNum(new StudentNum(
                gAuthUserInfo.getGrade(),
                gAuthUserInfo.getClassNum(),
                gAuthUserInfo.getNum()
        ));
        auth.setAuthority(Authority.ROLE_STUDENT);
        return authRepository.save(auth);
    }

    private Auth saveTeacher(GAuthUserInfo gAuthUserInfo) {
        Auth auth = new Auth();
        auth.setEmail(gAuthUserInfo.getEmail());
        auth.setName(gAuthUserInfo.getName());
        auth.setStudentNum(new StudentNum(
                gAuthUserInfo.getGrade(),
                gAuthUserInfo.getClassNum(),
                gAuthUserInfo.getNum()
        ));
        auth.setAuthority(Authority.ROLE_TEACHER);
        return authRepository.save(auth);
    }

    private void saveRefreshToken(tokenResponse tokenResponse, Auth auth) {
        if (auth.getId() != null) {
            RefreshToken refreshToken = new RefreshToken();
            refreshToken.setRefreshToken(tokenResponse.getRefreshToken());
            refreshToken.setUserId(auth.getId());
            refreshToken.setExpiredAt(tokenResponse.getRefreshTokenExpiresIn());
            refreshRepository.save(refreshToken);
        }
    }
}