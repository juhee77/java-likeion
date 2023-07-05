package com.lahee.webclient;

import com.lahee.webclient.dto.BeerGetDto;
import com.lahee.webclient.dto.BeerPostDto;
import com.lahee.webclient.dto.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class BeerRestService {
    public void getBeerObject() {
        //sprint 에서 제공하는 기본 HTTP Client
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://random-data-api.com/api/v2/beers";

        //rest template로 get 요청후 String -> Object(여기서는 String.class)로  역직렬화(문자열 -> 객체)
//        String responseBody = restTemplate.getForObject(url, String.class);
        BeerGetDto responseBody = restTemplate.getForObject(url, BeerGetDto.class); //필요 데이터만 넣으면 알아서 매핑해서 넣어준다.
        log.info("{}", responseBody);
    }


    public void getGearEntity() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://random-data-api.com/api/v2/beers";

        // RestController
        ResponseEntity<BeerGetDto> response = restTemplate.getForEntity(url, BeerGetDto.class);
        log.info("{}", response.getStatusCode());
        log.info("{}", response.getBody());
        log.info("{}", response.getHeaders());
    }

    public void postBeerEntity() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/give-me-beer";

        // RestController
        BeerPostDto dto = new BeerPostDto();
//        String response = restTemplate.postForObject(url, dto, String.class);
        MessageDto response = restTemplate.postForObject(url, dto, MessageDto.class);
        log.info(response.toString());
    }

    public void deleteBeerEntity() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/give-me-beer";

        // RestController
        BeerPostDto dto = new BeerPostDto();
        //delete와 같이 아무것도 반환값이 없는 경우 사용하는 방법
        ResponseEntity<Void> response = restTemplate.postForEntity(url, dto, Void.class);
        log.info(response.toString());
    }

    public void postBeerObject() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/give-me-beer";

        // RestController
        BeerPostDto dto = new BeerPostDto("hehe", 123L, 4.5);
        ResponseEntity response = restTemplate.postForEntity(url, dto, String.class);
        log.info("{}", response.getStatusCode());
        log.info("{}", response.getBody());
        log.info("{}", response.getHeaders());
    }
}
