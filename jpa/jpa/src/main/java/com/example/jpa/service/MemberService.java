package com.example.jpa.service;

import com.example.jpa.dto.MemberDto;
import com.example.jpa.entity.Member;
import com.example.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberDto read(Long id){
        Member member = memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        MemberDto memberDto = new MemberDto(member);

        memberDto.getRole().getName();

        return memberDto;
    }
}
