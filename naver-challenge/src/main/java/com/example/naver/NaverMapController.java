package com.example.naver;

import com.example.naver.config.WebClientConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class NaverMapController {
    private final WebClientConfig webClientConfig;

    @GetMapping("map")
    public String map(Model model) {
        model.addAttribute("ncpClientId", webClientConfig.getNcpApiClientId());
        return "map";
    }
}
