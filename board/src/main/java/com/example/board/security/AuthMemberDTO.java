package com.example.board.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@Getter
public class AuthMemberDTO extends User {

    private String email;
    private String name;
    private boolean fromSocial;
    private String password;

    // username : id 개념
    public AuthMemberDTO(String username, String password, boolean fromSocial,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.email = username;
        this.password = password;
        this.fromSocial = fromSocial;
    }
}
