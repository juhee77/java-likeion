package com.lahee.jpa.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppApiController {

    @GetMapping("/rest-controller")
    public String restController(){
        return "rest controller";
    }
}
