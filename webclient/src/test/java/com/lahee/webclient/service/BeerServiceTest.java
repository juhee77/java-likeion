package com.lahee.webclient.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BeerServiceTest {
    @Autowired
    BeerService beerService;

    @Test
    void drinkBeer() {
        beerService.drinkBeer();
    }

    @Test
    void postBeer() {
        beerService.postBeer();
    }
}