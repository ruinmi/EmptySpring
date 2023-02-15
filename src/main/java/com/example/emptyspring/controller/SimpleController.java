package com.example.emptyspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by elio on 15/02/2023
 */
@Controller
public class SimpleController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
