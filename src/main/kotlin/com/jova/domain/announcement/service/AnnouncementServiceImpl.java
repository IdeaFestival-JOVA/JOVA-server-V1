package com.jova.domain.announcement.service;

import com.jova.domain.announcement.dto.AnnouncementRequestDTO;
import com.jova.domain.announcement.entity.Announcement;
import com.jova.domain.announcement.repository.AnnouncementRepository;
import com.jova.domain.announcement.service.impl.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {
    private final AnnouncementRepository announcementRepository;

    @Override
    public Announcement createAnnouncement(Announcement announcement) {
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
    public void DeleteAnnouncement(Long id) {
        announcementRepository.deleteById(id);
    }

}
