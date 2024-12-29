package com.jova.domain.application.entity;

import com.jova.domain.user.UserMajor;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="application")
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class Application {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="유저전공",nullable = false, unique = true)
    private UserMajor major;

    @Column(name="내용")
    private String content;

    @Column(name="작성된시간", nullable=false)
    private String createdAt;

}
