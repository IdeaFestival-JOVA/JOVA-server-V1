package com.jova.domain.announcement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name="공지이름")
    private String title;
    @Column(name="공지내용")
    private String content;
    @Column(name="공지작성시간")
    private LocalDateTime createdAt;
    @Column(name="작성자", nullable = false)
    private String author;
}
