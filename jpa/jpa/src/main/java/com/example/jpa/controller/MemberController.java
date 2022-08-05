package com.example.jpa.controller;

import com.example.jpa.dto.MemberDto;
import com.example.jpa.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping()
    public ResponseEntity<MemberDto> read(Long id){
        MemberDto memberDto = memberService.read(id);

        return ResponseEntity.status(HttpStatus.OK).body(memberDto);
    }

}
