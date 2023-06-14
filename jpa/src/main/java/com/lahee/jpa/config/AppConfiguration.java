package com.lahee.jpa.config;

import com.google.gson.Gson;
import com.lahee.jpa.domain.AppConfigData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public AppConfigData connectionUrl() {
        //이 메소드의 반환값을 빈 객체로 등록
        if (true/*현재 나의 상황에 따라서 다른 URL을 제공하는 코드*/) {
            return new AppConfigData("main-url");
        }
        return new AppConfigData("backup-url");
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }
}

/*
@Configuration을 사용하여 구성 클래스를 정의하고
그 내부에서 @Bean 어노테이션을 사용하여 복잡한 객체 빈을 생성 및 구성할 수 있습니다.
 */
