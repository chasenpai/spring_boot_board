package com.board.entity;

import com.board.constant.Role;
import com.board.dto.MemberDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "state")
    private String state;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "last_login_date")
    private LocalDateTime lastLoginDate;


    public Member(MemberDto dto){
        this.id = dto.getId();
        this.username = dto.getUsername();
        this.password = dto.getPassword();
        this.state = dto.getState();
        this.role = dto.getRole();
    }

    public void deleteMember(String state){
        this.state = state;
    }

    public void updateLastLoginDate(){
        this.lastLoginDate = LocalDateTime.now();
    }

    public void changePassword(String password){
        this.password = password;
    }













}
