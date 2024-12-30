package com.jova.domain.announcement.controller;

import com.jova.domain.announcement.dto.AnnouncementRequestDTO;
import com.jova.domain.announcement.entity.Announcement;
import com.jova.domain.announcement.service.impl.AnnouncementServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/announcements")
public class AnnouncementController {
    private final AnnouncementServiceImpl announcementService;

    @GetMapping("/list")
    public List<Announcement> getAllAnnouncements() {
        return announcementService.findAllAnnouncements();
    }

    @GetMapping("/{id}")
    public Announcement getAnnouncementById(@PathVariable Long id) {
        return announcementService.findAnnouncementById(id);
    }

    @PostMapping
    public void createAnnouncement(@RequestBody AnnouncementRequestDTO announcementRequestDTO) {
        announcementService.saveAnnouncement(announcementRequestDTO.toEntity());
    }

    @DeleteMapping("/{id}")
    public void deleteAnnouncement(@PathVariable Long id) {
        announcementService.deleteAnnouncement(id);
    }
}
