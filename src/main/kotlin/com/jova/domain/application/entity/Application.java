package com.jova.domain.application.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Application {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long application_id;
    @Column(nullable = false, name="applicant")
    private String applicant;
    @Column(nullable = false, name="appcontent")
    private String application_content;


}
