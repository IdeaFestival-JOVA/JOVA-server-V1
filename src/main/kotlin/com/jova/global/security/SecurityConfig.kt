package com.jova.global.security

import com.jova.global.security.filter.JwtFilter
import com.jova.global.security.jwt.service.JwtProvider
import dev.yangsijun.gauth.configurer.GAuthLoginConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val gAuthLoginConfigurer: GAuthLoginConfigurer<HttpSecurity>, private val jwtProvider: JwtProvider
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it.disable() }.cors { it.configurationSource(corsConfigurationSource()) }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { request ->
                request
                    .requestMatchers(
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/gauth/authorization",
                        "/auth/reissue",
                        "/auth/logout",
                        "/auth/login"
                ).permitAll()
                    .requestMatchers("/role/teacher").hasAuthority("GAUTH_ROLE_TEACHER").anyRequest().permitAll()
            }.addFilterBefore(JwtFilter(jwtProvider), UsernamePasswordAuthenticationFilter::class.java)
        gAuthLoginConfigurer.configure(http)
        return http.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val unrestrictedCorsConfig = CorsConfiguration().apply {
            allowedOrigins = listOf(
                "http://localhost:5173",
                "https://jova-fork.vercel.app/",
                "http://localhost:8080",
                "https://port-0-jova-backend-m0kvtwm45b2f2eb2.sel4.cloudtype.app"
            )
            allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
            allowedHeaders = listOf("Authorization", "Content-Type")
            allowCredentials = true
        }
        val restrictedCorsConfig = CorsConfiguration().apply {
            allowedOrigins = listOf(
                "http://localhost:5173",
                "https://jova-fork.vercel.app/",
                "http://localhost:8080",
                "https://port-0-jova-backend-m0kvtwm45b2f2eb2.sel4.cloudtype.app"
            )
            allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
            allowedHeaders = listOf("Authorization", "Content-Type")
            allowCredentials = true
            exposedHeaders = listOf("Authorization")
        }
        val source = UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/swagger-ui/**", unrestrictedCorsConfig)
            registerCorsConfiguration("/v3/api-docs/**", unrestrictedCorsConfig)
            registerCorsConfiguration("/gauth/authorization", restrictedCorsConfig)
            registerCorsConfiguration("/auth/login", restrictedCorsConfig)
            registerCorsConfiguration("/auth/reissue", restrictedCorsConfig)
            registerCorsConfiguration("/auth/signup", restrictedCorsConfig)
            registerCorsConfiguration("/auth/logout", restrictedCorsConfig)
            registerCorsConfiguration("/articles/**", restrictedCorsConfig)
            registerCorsConfiguration("/announcements/**", restrictedCorsConfig)
            registerCorsConfiguration("/user/**", restrictedCorsConfig)

        }
        return source
    }
}