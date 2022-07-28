package com.example.jpa.controller;

import com.example.jpa.dto.ValidTestDto;
import com.example.jpa.dto.ValidatedTestDto;
import com.example.jpa.group.ValidationGroup1;
import com.example.jpa.group.ValidationGroup2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/validation")
public class ValidationTestController {

    @PostMapping("/valid")
    public ResponseEntity<String> checkValid(@Valid @RequestBody ValidTestDto validTestDto){
        return ResponseEntity.status(HttpStatus.OK).body(validTestDto.toString());
    }

    @PostMapping("/validated")
    public ResponseEntity<String> checkValidation(@Validated @RequestBody ValidatedTestDto validatedTestDto){
        return ResponseEntity.status(HttpStatus.OK).body(validatedTestDto.toString());
    }

    @PostMapping("/validated/group1")
    public ResponseEntity<String> checkValidation1(@Validated(ValidationGroup1.class) @RequestBody ValidatedTestDto validatedTestDto){
        return ResponseEntity.status(HttpStatus.OK).body(validatedTestDto.toString());
    }

    @PostMapping("/validated/group2")
    public ResponseEntity<String> checkValidation2(@Validated(ValidationGroup2.class) @RequestBody ValidatedTestDto validatedTestDto){
        return ResponseEntity.status(HttpStatus.OK).body(validatedTestDto.toString());
    }

    @PostMapping("/validated/all-group")
    public ResponseEntity<String> checkValidation3(@Validated({ValidationGroup1.class, ValidationGroup2.class})
                                                       @RequestBody ValidatedTestDto validatedTestDto){
        return ResponseEntity.status(HttpStatus.OK).body(validatedTestDto.toString());
    }

}
