package com.jova.domain.announcement.controller;

import com.jova.domain.announcement.dto.AnnouncementRequestDTO;
import com.jova.domain.announcement.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AnnouncementController {
    private final AnnouncementService announcementService;
    private AnnouncementRequestDTO announcementRequestDTO;
}
