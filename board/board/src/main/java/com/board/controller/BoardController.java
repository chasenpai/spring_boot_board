package com.board.controller;

import com.board.common.UserAccount;
import com.board.dto.BoardDto;;
import com.board.dto.SearchDto;
import com.board.service.BoardService;
import com.board.validator.BoardValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    private final BoardValidator boardValidator;

    @GetMapping({"/list", "/list/{page}"})
    public String list(@PathVariable("page") Optional<Integer> page, Model model, SearchDto searchDto){
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 10, Sort.Direction.DESC, "id");
        model.addAttribute("boards", boardService.list(pageable, searchDto));
        model.addAttribute("searchDto", searchDto);
        model.addAttribute("maxPage", 10);
        return "index";
    }

    @GetMapping({"/search", "/search/{page}"})
    public String search(@PathVariable("page") Optional<Integer> page, Model model,
                         @RequestParam("search") String search, @RequestParam("select") String select){
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 10, Sort.Direction.DESC, "id");
        model.addAttribute("boards", boardService.search(pageable, search, select));
        model.addAttribute("maxPage", 5);
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

        if(userAccount != null){
            model.addAttribute("username", userAccount.getMember().getUsername());
        }

        return "board/read";
    }

    @GetMapping("/update/{boardId}")
    public String update(@PathVariable("boardId") Long boardId, Model model){

        BoardDto boardDto = boardService.read(boardId);
        model.addAttribute("boardDto", boardDto);
        model.addAttribute("test", "test");

        return "board/write";
    }

    @PostMapping("/update/{boardId}")
    public String update(@Validated @ModelAttribute BoardDto boardDto, BindingResult bindingResult, Model model){
        boardValidator.validate(boardDto, bindingResult);
        if(bindingResult.hasErrors()){
            return "board/write";
        }
        boardService.update(boardDto);

        return "redirect:/boards/list";
    }

    @GetMapping("/delete/{boardId}")
    public String delete(@PathVariable ("boardId") Long boardId){
        boardService.delete(boardId);

        return "redirect:/boards/list";
    }



}
