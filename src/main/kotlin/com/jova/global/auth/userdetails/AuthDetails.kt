package com.jova.global.auth.userdetails

import com.jova.domain.auth.entity.Auth
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class AuthDetails(private val auth: Auth) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        val simpleGrantedAuthority = SimpleGrantedAuthority(auth.authority?.name ?: "ROLE_USER")
        return listOf(simpleGrantedAuthority)
    }

    override fun getPassword(): String? {
        return null
    }

    override fun getUsername(): String? {
        return auth.email
    }

    override fun isAccountNonExpired(): Boolean {
        return false
    }

    override fun isAccountNonLocked(): Boolean {
        return false
    }

    override fun isCredentialsNonExpired(): Boolean {
        return false
    }

    override fun isEnabled(): Boolean {
        return false
    }
}