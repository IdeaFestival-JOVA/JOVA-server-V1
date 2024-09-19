package com.jova.global.gauth

import gauth.GAuth
import gauth.impl.GAuthImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class gauthBean {
    @Bean
    fun gauth(): GAuth {
        return GAuthImpl()
    }
}