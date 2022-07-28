package com.board.common;

import com.board.entity.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
public class UserAccount extends User {

    private Member member;

    public UserAccount(Member member) {
        super(member.getUsername(), member.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        this.member = member;
    }
}
