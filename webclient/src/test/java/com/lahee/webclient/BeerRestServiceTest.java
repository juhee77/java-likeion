package com.lahee.webclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class BeerRestServiceTest {
    @Autowired
    BeerRestService beerRestService;

    @Test
    public void testGetBeerObject() {
        beerRestService.getBeerObject();
    }

    @Test
    public void voidTestGetHeader() {
        beerRestService.getGearEntity();
    }

    @Test
    public void voidPostEntityTest() {
        beerRestService.postBeerEntity();
    }

    @Test
    public void voidPostObjectTest() {
        beerRestService.postBeerObject();
    }

    @Test
    public void voidPostDeleteTest(){
        beerRestService.deleteBeerEntity();
    }

    @Test
    public void testExchange(){
        beerRestService.exchange();
    }

}