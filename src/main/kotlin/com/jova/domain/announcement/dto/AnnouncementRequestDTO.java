package com.jova.domain.announcement.dto;

import com.jova.domain.announcement.entity.Announcement;

public class AnnouncementRequestDTO {
    private String title;
    private String content;
    private String author;

    public Announcement toEntity() {
        return Announcement.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
