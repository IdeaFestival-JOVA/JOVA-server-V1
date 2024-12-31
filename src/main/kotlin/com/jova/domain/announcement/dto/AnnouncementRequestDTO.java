package com.jova.domain.announcement.dto;

import com.jova.domain.announcement.entity.Announcement;
import lombok.Builder;

import java.time.LocalDateTime;

public class AnnouncementRequestDTO {
    private String title;
    private String content;
    private String author;

    @Builder
    public AnnouncementRequestDTO(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Announcement toEntity() {
        return Announcement.builder()
                .title(title)
                .content(content)
                .author(author)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
