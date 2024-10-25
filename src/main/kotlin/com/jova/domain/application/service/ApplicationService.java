package com.jova.domain.application.service;

import com.jova.domain.application.entity.Application;
import com.jova.domain.application.repository.ApplicationRepository;
import com.jova.domain.auth.entity.Auth;
import com.jova.domain.auth.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final AuthRepository authRepository;



}
