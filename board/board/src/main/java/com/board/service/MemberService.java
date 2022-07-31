package com.board.service;

import com.board.common.UserAccount;
import com.board.constant.Role;
import com.board.dto.MemberDto;
import com.board.entity.Member;
import com.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberDto read(String username){
        Member member = memberRepository.findByUsername(username);
        MemberDto memberDto = new MemberDto(member);

        return memberDto;
    }

    public void save(MemberDto memberDto){
        memberDto.setRole(Role.ROLE_USER);

        Member member = new Member(memberDto);
        memberRepository.save(member);
    }

    @Transactional
    public Member lastLoginDate(String username){
        Member member = memberRepository.findByUsername(username);
        if(member != null){
            member.updateLastLoginDate();
        }
        return member;
    }

    @Transactional
    public void delete(Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new);
        member.deleteMember("삭제");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username);
        lastLoginDate(username);

        if(member == null || member.getState() != null){
            throw new UsernameNotFoundException(username);
        }

        return new UserAccount(member);
    }
}
