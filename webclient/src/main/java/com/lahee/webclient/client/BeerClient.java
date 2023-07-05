package com.lahee.webclient.client;

import com.lahee.webclient.dto.BeerGetDto;
import com.lahee.webclient.dto.BeerPostDto;

public interface BeerClient { //인터페이스 기반 의존성 주입
    BeerGetDto getBeer();

    boolean postBeer(BeerPostDto dto);
}