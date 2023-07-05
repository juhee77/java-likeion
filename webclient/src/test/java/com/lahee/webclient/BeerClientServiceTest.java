package com.lahee.webclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerClientServiceTest {
    @Autowired
    BeerClientService service;
    @Test
    public void test() {
        service.getBeer();
    }

}