package com.jova.domain.auth.vo

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.MappedSuperclass

@Embeddable
@MappedSuperclass
data class StudentNum(
    @Column(nullable = false, length = 1) val grade: Int,

    @Column(nullable = false, length = 1) val classNum: Int,

    @Column(nullable = false, length = 2) val number: Int
) {
    constructor() : this(0, 0, 0)

    fun generateStudentId(): Int {
        return (grade * 1000) + (classNum * 100) + number
    }
}