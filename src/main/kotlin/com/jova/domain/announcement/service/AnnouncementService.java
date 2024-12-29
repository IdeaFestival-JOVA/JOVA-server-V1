package com.jova.domain.announcement.service;

import com.jova.domain.announcement.entity.Announcement;

import java.util.List;

public interface AnnouncementService {
    Announcement saveAnnouncement(Announcement announcement);
    Announcement findAnnouncementById(Long id);
    List<Announcement> findAllAnnouncements();
    void deleteAnnouncement(Long id);
}
