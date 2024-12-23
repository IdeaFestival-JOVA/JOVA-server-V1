package com.jova.domain.application.controller;

import com.jova.domain.application.dto.request.ApplicationRequestDTO;
import com.jova.domain.application.entity.Application;
import com.jova.domain.application.repository.ApplicationRepository;

import com.jova.domain.application.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name="Application API", description = "지원서 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final ApplicationRepository applicationRepository;
    private ApplicationRequestDTO applicationRequestDTO;

    @PostMapping("")
    @Operation(summary = "지원서 생성", description = "지원서 작성하는 API")
    public Application createApplication(@RequestBody @NotBlank ApplicationRequestDTO applicationRequestDTO) {
        return applicationRepository.save(applicationRequestDTO.toEntity());
    }

    @GetMapping("/list")
    public List<Application> findAllApplications() {
        return applicationService.findAll();
    }

    @GetMapping("/{id}")
    public Application findApplicationById(@PathVariable Long id) {
        return applicationRepository.findById(id).orElse(null);
    }

    @PatchMapping("{id}")
    public Optional<Application> updateApplication(@PathVariable Long id, @RequestBody ApplicationRequestDTO applicationRequestDTO) {
        Optional<Application> existApplication = applicationService.findApplicationById(id);

        return existApplication;
    }
}
