package com.jova.domain.application.service;

import com.jova.domain.application.entity.Application;
import com.jova.domain.user.UserMajor;

import java.util.List;
import java.util.Optional;

public interface ApplicationService {
    Optional<Application> findApplicationByMajor(UserMajor major);
    List<Application> findAll();
    Optional<Application> findApplicationById(Long id);
}
