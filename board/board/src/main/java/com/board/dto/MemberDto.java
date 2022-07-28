package com.board.dto;

import com.board.constant.Role;
import com.board.entity.Member;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MemberDto {

    private Long id;

    private String username;

    private String password;

    private String pwdCheck;

    private String state;

    private Role role;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    private LocalDateTime lastLoginDate;

    public MemberDto(Member member){
        this.id = member.getId();
        this.username = member.getUsername();

    }


}
