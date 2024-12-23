package com.jova.domain.announcement.service.impl;

import com.jova.domain.announcement.entity.Announcement;

import java.util.List;

public interface AnnouncementService {
    Announcement createAnnouncement(Announcement announcement);
    Announcement findAnnouncementById(Long id);
    List<Announcement> findAllAnnouncements();
    void DeleteAnnouncement(Long id);
}
