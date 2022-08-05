package com.example.jpa.dto;

import com.example.jpa.entity.Member;
import com.example.jpa.entity.Role;
import lombok.Data;

@Data
public class MemberDto {

    private Long id;

    private String username;

    private String password;

    private Role role;

    public MemberDto(Member member){
        this.id = member.getId();;
        this.username = member.getUsername();
        this.password = member.getPassword();
        this.role = member.getRole();
    }
}
