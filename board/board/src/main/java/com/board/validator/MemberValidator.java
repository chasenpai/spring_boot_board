package com.board.validator;

import com.board.dto.MemberDto;
import com.board.entity.Member;
import com.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberValidator implements Validator {

    private final MemberRepository memberRepository;

    private static final int MIN_SIZE = 8;

    private static final int MAX_SIZE = 50;

    private static final String regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{"
            + MIN_SIZE + "," + MAX_SIZE + "}$";

    @Override
    public boolean supports(Class<?> clazz) {
        return MemberDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberDto memberDto = (MemberDto) target;

        Member member = memberRepository.findByUsername(memberDto.getUsername());

        if(StringUtils.isEmpty(memberDto.getUsername())){
            errors.rejectValue("username", "please.enter.id");
        }
        if(member != null){
            errors.rejectValue("username", "already.registered.id");
        }
        if(StringUtils.isEmpty(memberDto.getPassword())){
            errors.rejectValue("password", "please.enter.password");
        }
        if(!StringUtils.equals(memberDto.getPassword(), memberDto.getPwdCheck())){
            errors.rejectValue("pwdCheck", "password.different");
        }
        if(!pwdValid(Arrays.asList(memberDto.getPassword(), memberDto.getPwdCheck()))){
            errors.rejectValue("password", "password.rules", new Object[]{MIN_SIZE, MAX_SIZE},
                                        MIN_SIZE + "자 이상 " + MAX_SIZE + "자 이하의 숫자, 영문자, 특수문자를 포함한 비밀번호를 입력해 주세요.");
        }

    }

    private boolean pwdValid(List<String> password){
        return password.stream().allMatch(pwd -> (pwd.matches(regexp) == true));
    }
}
