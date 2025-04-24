package com.example.book.controller;

import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Controller;

import groovy.util.logging.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@ReadingConverter
@Controller
public class HomeController {

    @GetMapping("/")
    public String getHome() {
        return "redirect:/book/list";
    }
}
