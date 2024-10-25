package com.jova.domain.application.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Application API", description = "지원서 관리 API")
@RestController
@RequestMapping("articles/{id}/application")
public class ApplicationController {
}
