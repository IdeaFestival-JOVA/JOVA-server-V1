package com.jova.domain.auth.entity

import com.jova.domain.auth.enums.Authority
import com.jova.domain.auth.vo.StudentNum
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.util.UUID

@Entity
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
data class Auth(
    @Id @GeneratedValue(generator = "UUID") val authid: UUID? = null,

    var email: String? = null,

    var name: String? = null,

    @Embedded var studentNum: StudentNum? = null,

    @Enumerated(EnumType.STRING) var authority: Authority? = null
)