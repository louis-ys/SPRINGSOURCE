package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class GinDTO {
    @NotBlank(message = "아이디를 입력해주세요")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;
}
