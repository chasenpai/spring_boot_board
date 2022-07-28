package com.example.jpa.dto;

import com.example.jpa.group.ValidationGroup1;
import com.example.jpa.group.ValidationGroup2;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidatedTestDto {

    @NotBlank
    private String name;

    @Email
    String email;

    @Pattern(regexp = "01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
    String phone;

    @Min(value = 20, groups = ValidationGroup1.class)
    @Max(value = 60, groups = ValidationGroup1.class)
    int age;

    @Size(min = 0, max = 100)
    String description;

    @Positive(groups = ValidationGroup2.class)
    int count;

    @AssertTrue
    boolean booleanChk;
}
