package com.example.auth.oauth;

import com.example.auth.jwt.JwtTokenUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
//OAuth2 통신이 성공적으로 끝났을때 사용한느 클래스
//JWT를 활용한 인증 구현하고 있기 떄문에
//ID  Provider에게 받은 정보를 바탕으로 JWT 발급하는 역할을 하는 용도
//JWT를 발급하고 클라이언트가 저장할 수 있도록 특정 URL로 리다이렉트 시키자
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    //인증 성공 후 특정 URL로 리다이렉트 시키고 싶을때 활용될 수 있는
    //success handler

    private final JwtTokenUtils jwtTokenUtils;

    public OAuth2SuccessHandler(JwtTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //OAuth2 User service impl에서 반환한 DefaultOAuth2User가 저장된다.
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String jwt = jwtTokenUtils.generateToken
                (User.withUsername(oAuth2User.getName()).password(oAuth2User.getAttribute("id").toString()).build());

        //목적지 URL설정
        //우리 서비스에 Frontend 구성에 따라 유연하게 대처한다.
        String targetUrl = String.format("http://localhost:8080/token/val?token=%s", jwt);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);

    }
}
