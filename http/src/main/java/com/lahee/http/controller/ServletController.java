package com.lahee.http.controller;

import com.lahee.http.dto.ResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Locale;

import static com.lahee.http.dto.Status.SUCCESS;

@Slf4j
@Controller
public class ServletController {
    @PostMapping("/servlet")
    public ResponseEntity<ResponseDto> servletHandling(HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {
        BufferedReader reader = httpServletRequest.getReader();
        reader.lines().forEach(log::info);

        //!!!!
        response.getHeaderNames().forEach(headerName -> log.info("{} : {}", headerName, response.getHeader(headerName)));


        return ResponseEntity.status(HttpStatus.CREATED)
                .headers(new HttpHeaders(CollectionUtils.toMultiValueMap(new LinkedCaseInsensitiveMap<>(8, Locale.ENGLISH))))
                .body(ResponseDto.from(SUCCESS));

    }
}
