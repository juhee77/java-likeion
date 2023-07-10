package com.lahee.security.jwt;

import com.lahee.security.entity.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    //사용자가 Header에 포함도니 jwt를 해석한후에
    // 그에따라 사용자의 인증상태를 확인한다.
    private final JwtTokenUtils jwtTokenUtils;

    public JwtTokenFilter(JwtTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //jwt가 포함되어 있다면 포함되어있는 헤더를 요청
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        //authorization이 널이 아니면서 "Bearer "로 구성되어 있어야 정상적인 인증 정보다.

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String[] split = authorizationHeader.split(" ");
            if (split.length == 2 && jwtTokenUtils.validateJwt(split[1])) {
                String token = split[1];
                log.info("{} 토큰이 인증되었습니다. ", token);

//                SecurityContextHolder.getContext().setAuthentication(); --> 시큐리티 공식 문서에서 추천하지않음
                //공식문서 추천방법
                SecurityContext context = SecurityContextHolder.createEmptyContext();

                //jwt에서 사용자 이름을 가지고 온다(아까 claim 에 저장함)
                String username = jwtTokenUtils.parseClaims(token).getSubject();

                //사용자 인증 정보 생성
                AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        CustomUserDetails.builder().username(username).build(),
                        token, new ArrayList<>()
                );
                //Object principal, Object credentials,Collection<? extends GrantedAuthority> authorities


                //securityContext에 사용자 정보 설정
                context.setAuthentication(authenticationToken);
                //securityContextHodler에 SecurityContext 설정
                SecurityContextHolder.setContext(context);
                log.info("set Security context with jwt"); //jwt를 가지고 사용자가 누구인지 까지 판단한 단계
            } else {
                log.warn("잘못된 JWT 토큰 입니다.");
            }
        }
        filterChain.doFilter(request, response);
    }
}
