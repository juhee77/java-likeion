package com.example.article;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class QueryController {
    //GET /path?query=keyword&limit=20
    @GetMapping("/path")
    public Map<String, Object> queryParams(@RequestParam(required = false,defaultValue ="default", name = "query") String query,
                                           @RequestParam(required = true, name = "limit") Integer limit,
                                           @RequestParam("query2") String query2) {
        log.info("query : {}", query);
        log.info("query2 : {}", query2);
        log.info("limit : {}", limit);

        Map<String, Object> response = new HashMap<>();
        response.put("query", query);
        response.put("query2", query2);
        response.put("limit", limit);
        return response;
    }
}
