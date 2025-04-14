package com.example.demo.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Log4j2
@RequestMapping("/member")
@Controller
public class MemberController {
    @GetMapping("/register")
    public void getregister() {
        log.info("회원가입");
    }

    @GetMapping("/login")
    public void getlogin() {
        log.info("로그인");
    }

    @GetMapping("/logout")
    public void getlogout() {
        log.info("로그아웃");
    }

    @GetMapping("/change")
    public void getchange() {
        log.info(" 변경");
    }

}
