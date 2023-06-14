package com.lahee.jpa.config;

import com.google.gson.Gson;
import com.lahee.jpa.domain.AppConfigData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * controller, service, respository 가 아닌
 * 평범한 bean 객체이다.
 * 외부 API사용의 경우나 공유 기능 개발, 등 그냥 IOC등록이 필요한 경우에 사용한다.
 */
@Component
@RequiredArgsConstructor
public class AppComponent {

    private final AppConfigData appConfigDate;
    private final Gson gson;
//    private final String connectionUrl;

    public void useApi(){

    }
}

/*
@Component를 사용하여 단순한 POJO 클래스를 빈으로 등록하고 사용할 수 있다.
 */
