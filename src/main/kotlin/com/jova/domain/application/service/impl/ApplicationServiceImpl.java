package com.jova.domain.application.service.impl;

import com.jova.domain.application.entity.Application;
import com.jova.domain.application.repository.ApplicationRepository;
import com.jova.domain.application.service.ApplicationService;
import com.jova.domain.auth.repository.AuthRepository;
import com.jova.domain.user.UserMajor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final AuthRepository authRepository;

    @Override
    public Optional<Application> findApplicationByMajor(UserMajor major) {
        return applicationRepository.findByMajor(major);
    }

    @Override
    public List<Application> findAll(){
        return applicationRepository.findAll();
    }

    @Override
    public Optional<Application> findApplicationById(Long id) {
        return applicationRepository.findById(id);
    }
}