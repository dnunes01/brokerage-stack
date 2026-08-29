package com.danprep.brokerage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/alive")
    public String alive() {
        return "I am alive!";
    }
}
