package com.board.validator;

import com.board.dto.BoardDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Component
public class BoardValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return BoardDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BoardDto boardDto = (BoardDto) target;

        if(StringUtils.isEmpty(boardDto.getTitle())){
            errors.rejectValue("title", "please.enter.title");
        }
        if(StringUtils.isEmpty(boardDto.getContent())){
            errors.rejectValue("content", "please.enter.content");
        }

    }
}
