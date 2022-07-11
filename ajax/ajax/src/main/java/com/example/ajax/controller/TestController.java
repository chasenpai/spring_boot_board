package com.example.ajax.controller;

import com.example.ajax.dto.TestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {

    @PostMapping(value = "/dataSend")
    public String test(Model model, TestDto testDto){
       model.addAttribute("msg", testDto.getAge());
       return "index :: #result";
    }


}
