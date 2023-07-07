package com.lahee.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.sql.Date;
import java.time.Instant;

@Slf4j
@Component
public class JwtTokenUtils {
//jwt 관련 기능을 넣어두기 위한
    private final Key signinKey;
    public JwtTokenUtils(@Value("${jwt.secret}") String jwtSecret) {
        log.info(jwtSecret);
        this.signinKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    //주어진 사용자 정보를 바탕으로 JWT를 문자열로 생성한다.
    public String generateToken(UserDetails userDetails) { //토큰 발급
        // Claims : JWT에 담기는 정보의 단위를 claim이라 부른다.
        //        : Claims는 Claim들을 담기위한 Map의 상속 interface
        Claims jwtClaims = Jwts.claims().setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(Instant.now().plusSeconds(3600)));

        return Jwts.builder().setClaims(jwtClaims).signWith(signinKey).compact();
    }
}