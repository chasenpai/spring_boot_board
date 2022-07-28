package com.board.controller;

import com.board.common.UserAccount;
import com.board.dto.MemberDto;
import com.board.service.MemberService;
import com.board.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    private final PasswordEncoder passwordEncoder;
    private final MemberValidator memberValidator;

    @GetMapping("/login")
    public String login(){
        return "member/login";
    }

    @GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("loginError", "아이디 또는 비밀번호 오류입니다.");
        return "member/login";
    }

    @GetMapping("/join")
    public String join(Model model){
        model.addAttribute("memberDto", new MemberDto());
        return "member/join";
    }

    @PostMapping("/join")
    public String save(@Validated @ModelAttribute MemberDto memberDto, BindingResult bindingResult, Model model){
        memberValidator.validate(memberDto, bindingResult);
        if(bindingResult.hasErrors()){
            return "member/join";
        }
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        memberService.save(memberDto);

        return "redirect:/boards/list";
    }

    @GetMapping("/info")
    public String info(Model model, @AuthenticationPrincipal UserAccount userAccount){
        String username = "";
        if(userAccount != null){
            username = userAccount.getMember().getUsername();
        }
        MemberDto memberDto = memberService.read(username);
        model.addAttribute("member", memberDto);
        model.addAttribute("memberDto", new MemberDto());

        return "member/join";
    }

    @GetMapping("/delete/{memberId}")
    public String delete(@PathVariable("memberId") Long memberId){

        memberService.delete(memberId);
        return "redirect:/members/logout";
    }
}
