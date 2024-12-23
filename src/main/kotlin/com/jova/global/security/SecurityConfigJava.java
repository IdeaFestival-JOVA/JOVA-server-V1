package com.jova.global.security;

import com.jova.global.security.filter.JwtFilterJava;
import com.jova.global.security.jwt.service.JwtTokenProviderJava;
import dev.yangsijun.gauth.configurer.GAuthLoginConfigurer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfigJava {

    private final GAuthLoginConfigurer<HttpSecurity> gAuthLoginConfigurer;
    private final JwtTokenProviderJava jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/swagger-ui/**", "v3/api-docs/**").permitAll()
                        .requestMatchers("/gauth/authorization", "auth/reissue", "auth/logout", "auth/login").permitAll()
                        .requestMatchers("/auth").authenticated()
                        .requestMatchers("/role/student").hasAuthority("GAUTH_ROLE_STUDENT")
                        .requestMatchers("/role/teacher").hasAuthority("GAUTH_ROLE_TEACHER")
                        .anyRequest().permitAll()
                )
                .addFilterBefore(new JwtFilterJava(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        gAuthLoginConfigurer.configure(http);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration unrestrictedCorsConfig = new CorsConfiguration();
        unrestrictedCorsConfig.setAllowedOrigins(List.of("*"));
        unrestrictedCorsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        unrestrictedCorsConfig.setAllowedHeaders(List.of("Authorization", "Context-Type"));
        unrestrictedCorsConfig.setAllowCredentials(false);

        CorsConfiguration restrictedCorsConfig = new CorsConfiguration();
        restrictedCorsConfig.setAllowedOrigins(List.of("*"));
        restrictedCorsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        restrictedCorsConfig.setAllowedHeaders(List.of("Authorization", "Context-Type"));
        restrictedCorsConfig.setAllowCredentials(false);
        restrictedCorsConfig.setExposedHeaders(List.of("Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/swaager-ui/**", unrestrictedCorsConfig);
        source.registerCorsConfiguration("/v3/api-docs/**", unrestrictedCorsConfig);
        source.registerCorsConfiguration("/gauth/authorization", unrestrictedCorsConfig);
        source.registerCorsConfiguration("/auth/reissue", unrestrictedCorsConfig);
        source.registerCorsConfiguration("/auth/logout", unrestrictedCorsConfig);
        source.registerCorsConfiguration("/auth/login", unrestrictedCorsConfig);

        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
