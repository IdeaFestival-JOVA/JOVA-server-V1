package com.jova.domain.announcement.service.impl;

import com.jova.domain.announcement.entity.Announcement;
import com.jova.domain.announcement.repository.AnnouncementRepository;
import com.jova.domain.announcement.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {
    private final AnnouncementRepository announcementRepository;

    @Override
    public Announcement saveAnnouncement(Announcement announcement) {
        return announcementRepository.save(announcement);
    }

    @Override
    public Announcement findAnnouncementById(Long id) {
        return announcementRepository.findById(id).orElseThrow( () -> new IllegalArgumentException("공지를 찾을 수 없습니다."));
    }

    @Override
    public List<Announcement> findAllAnnouncements() {
        return announcementRepository.findAll();
    }

    @Override
    public void deleteAnnouncement(Long id) {
        announcementRepository.deleteById(id);
    }

}
