package com.example.auth.config;

import com.example.auth.jwt.JwtTokenFilter;
import com.example.auth.oauth.OAuth2SuccessHandler;
import com.example.auth.oauth.OAuth2UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class WebSecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final OAuth2UserServiceImpl oAuth2UserService;

    public WebSecurityConfig(JwtTokenFilter jwtTokenFilter, OAuth2SuccessHandler oAuth2SuccessHandler, OAuth2UserServiceImpl oAuth2UserService) {
        this.jwtTokenFilter = jwtTokenFilter;
        this.oAuth2SuccessHandler = oAuth2SuccessHandler;
        this.oAuth2UserService = oAuth2UserService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    )
            throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authHttp -> authHttp
                        .requestMatchers("/token/**", "/views/**")
                        .permitAll()
                )
                .oauth2Login(oauth2login -> oauth2login
                        .loginPage("/views/login")
                        .successHandler(oAuth2SuccessHandler)
                        .userInfoEndpoint(userinfo -> userinfo
                                .userService(oAuth2UserService)
                        )
                )
                .sessionManagement(
                        sessionManagement -> sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtTokenFilter, AuthorizationFilter.class);


        return http.build();
    }
}
