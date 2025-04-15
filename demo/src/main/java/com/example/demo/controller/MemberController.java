package com.example.demo.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@RequestMapping("/member")
@Controller
public class MemberController {
    @GetMapping("/register")
    public void getregister() {
        log.info("회원가입");
    }

    @PostMapping("/register")
    public String postregister(@ModelAttribute("mDTO") MemberDTO memberDTO) {
        log.info("회원가입 요청 {}", memberDTO);
        return "redirect:/member/login";
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

    @PostMapping("/login")
    // public void postlogin(String userid, String password) {
    // public void postlogin(LoginDTO loginDTO) {
    public void postlogin(HttpServletRequest request) {
        // HttpServletRequest 사용자의 정보및 서버쪽 정보 추출
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        String remote = request.getRemoteAddr();
        String local = request.getLocalAddr();
        log.info("로그인 요청 {}, {}", userid, password);
        log.info("클라이언트 정보 {}, {}", remote, local);

    }

}
