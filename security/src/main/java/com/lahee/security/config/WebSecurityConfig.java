package com.lahee.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableWebSecurity // 2.1 버전이후 스프링 스타터 시큐리티에서는 필수 아님
public class WebSecurityConfig {

    @Bean //메서드의 결과를 bean 객체로 등록해주는 어노테이션
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) //cross site request 방지를 위한 추가(실제 배포 시에는 넣지 않는게 좋다)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .authorizeHttpRequests(
                        //HTTP 요청 허가 관련 설정
                        authHttp -> authHttp.requestMatchers("/no-auth", "/users/register").permitAll() //누구든지 요청하는 허가
                                .requestMatchers("/re-auth", "/users/my-profile").authenticated() //인증이 된 사용자만 허가
                                .requestMatchers("/").anonymous()//인증이 되지 않은 사용자만 허가
//                        .anyRequest().authenticated() //나머지 요청에 대해서 인증된 사용자만
                ).formLogin( //쿠키를 통해 세션을 생성한다 (아이디, 비밀번호)
                        formLogin -> formLogin
                                .loginPage("/users/login") //로그인 페이지
                                .defaultSuccessUrl("/users/my-profile") //로그인 성공
                                .failureUrl("/users/login?fail")//로그인 실패
                                .permitAll()//로그인 과정에서 필요한 과정은 모든 사용자사 사용할 수 있도록 권한을 설정한다.
                ).logout( //세션을 제거한다. 세션 정보만 있으면 제거할 수 있다.
                        logout -> logout //로그아웃 으로 포스트 요청을 보내게끔한다.
                                .logoutUrl("/users/logout")
                                .logoutSuccessUrl("/users/login") //로그아웃 성공시에
                )

        ;
        return http.build();
    }

    /**
     * 사용자 관리를 위한 인터페이스 구현체 bean
     */
//    @Bean
    public UserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder) {
        //스프링에서 미리 만들어 놓은 사용자 인증 서비스 (위의 시큐리티 처리과정에서 해당 과정을 사용한다.)
        // 임시 User
        UserDetails user1 = User.withUsername("qwer")
                .password(passwordEncoder.encode("qwer"))
                .build();

        UserDetails user2 = User.withUsername("zxcv")
                .password(passwordEncoder.encode("zxcv"))
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //비밀번호는 기본적으로 해독가능한 형태로 디비에 저장하면 안된다.
        // 따라서 기본적으로 비밀번호 단방향 암호화 하는 인코더를 사용한다.
        return new BCryptPasswordEncoder();
    }
}
