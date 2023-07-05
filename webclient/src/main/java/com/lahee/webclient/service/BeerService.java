package com.lahee.webclient.service;

import com.lahee.webclient.dto.BeerGetDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BeerService {
    private final BeerRestClient client;

    public BeerService(BeerRestClient beerRestClient) {
        this.client = beerRestClient;
    }


    public void drinkBeer() {
        log.info("order beer");

        //API 활용하여 맥주 정보를 받아온다.
        //맥주 정보를 받아오는것은 비즈니스 로직에서 벗어난건 아닐까? 서비스를 분리하자 -> beerService

        BeerGetDto beer= client.getBeer(); //template 사용방법
        log.info("{}는 맛있다!", beer.getName());

    }
}
