package com.lahee.security.jwt;

import com.lahee.security.dto.JwtRequestDto;
import com.lahee.security.dto.JwtResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping("/token") //http://localhost:8080/token/** 로 부터 시작하는 url에 대해서
@RequiredArgsConstructor
public class TokenController {
    //UserDetailManager : 사용자 정보 회수
    //PasswordEncoder : 비밀번호 대조용 인코더
    //토큰이 정상 토큰인지 확인하고 데이터를 전달한다.

    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtils;

    //Q 생성자 방식이 나은건가?

    @PostMapping("/issue")
    public JwtResponseDto issueJwt(@RequestBody JwtRequestDto dto) {
        UserDetails userDetails = userDetailsManager.loadUserByUsername(dto.getUsername());

        log.info(dto.getPassword());
        log.info(userDetails.getPassword());
        if (!passwordEncoder.matches(dto.getPassword(), userDetails.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        String token = jwtTokenUtils.generateToken(userDetails);
        return new JwtResponseDto(token);

    }
}
