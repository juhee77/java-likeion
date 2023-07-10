package com.lahee.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
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
    private final JwtParser jwtParser;

    public JwtTokenUtils(@Value("${jwt.secret}") String jwtSecret) {
        log.info(jwtSecret);
        this.signinKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        //JWT 번역기
        this.jwtParser = Jwts.parserBuilder().setSigningKey(this.signinKey).build();
    }

    //주어진 사용자 정보를 바탕으로 JWT를 문자열로 생성한다.
    public String generateToken(UserDetails userDetails) { //토큰 발급
        // Claims : JWT에 담기는 정보의 단위를 claim이라 부른다.
        //        : Claims는 Claim들을 담기위한 Map의 상속 interface
        Claims jwtClaims = Jwts.claims().setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(Instant.now().plusSeconds(3600)));

        return Jwts.builder().setClaims(jwtClaims).signWith(signinKey).compact();
    }


    // jwt가 유효한지 판단하는 메서드
    // jjwt 라이브러리에서는 JW를 해석하는 과정에서 유효하지 않은경우 예외를 발생시킨다.
    public boolean validateJwt(String token) {
        // 정당하면 true 리턴
        try {
            jwtParser.parseClaimsJws(token); //암호화된 jwt를 해석하기 위한 메서드
            return true;
        } catch (Exception e) {
            log.info("error : {}", e.getMessage());
        }
        return false;
    }


    // JWT를 인자로 받고, 그 JWT를 해석해서
    // 사용자 정보를 회수하는 메소드
    public Claims parseClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

}