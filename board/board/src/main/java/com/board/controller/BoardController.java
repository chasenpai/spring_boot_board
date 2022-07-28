package com.board.controller;

import com.board.common.UserAccount;
import com.board.dto.BoardDto;
import com.board.service.BoardService;
import com.board.validator.BoardValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    private final BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("boards", boardService.list());
        return "index";
    }


    @GetMapping("/write")
    public String write(Model model){
        model.addAttribute("boardDto", new BoardDto());
        return "board/write";
    }

    @PostMapping("/write")
    public String save(@Validated @ModelAttribute BoardDto boardDto, BindingResult bindingResult, Model model ){
        boardValidator.validate(boardDto, bindingResult);
        if(bindingResult.hasErrors()){
            return "board/write";
        }
        boardService.save(boardDto);

        return "redirect:/boards/list";
    }

    @GetMapping("/{boardId}")
    public String read(@PathVariable("boardId") Long boardId, Model model, @AuthenticationPrincipal UserAccount userAccount){
        BoardDto boardDto = boardService.read(boardId);
        model.addAttribute("boardDto", boardDto);

        return "board/read";
    }

}
