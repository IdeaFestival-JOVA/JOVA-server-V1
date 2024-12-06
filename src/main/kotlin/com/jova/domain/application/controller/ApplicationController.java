package com.jova.domain.application.controller;

import com.jova.domain.application.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Application API", description = "지원서 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("articles/{id}/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    @Operation(summary = "지원서 생성", description = "지원서 작성하는 API")
    public String createApplication(@PathVariable("id") Long id) {
        return "success";
    }
}
