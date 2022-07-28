package com.example.jpa.dto;

import com.example.jpa.common.Phone;
import lombok.*;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidTestDto {

    @NotBlank
    String name;

    @Email
    String email;

    @Phone
    String phone;

    @Min(value = 20) @Max(value = 60)
    int age;

    @Size(min = 0, max = 100)
    String description;

    @Positive
    int count;

    @AssertTrue
    boolean booleanChk;


    /*
     * 문자열 검증
     * @Null - null 값만 허용한다.
     * @NotNull - null을 허용하지 않음, ""는 허용한다.
     * @NotEmpty - null, ""을 허용하지 않음. " "는 허용한다.
     * @NotBlank - null, "", " "을 허용하지 않는다.
     *
     * 최대/최솟값 검증
     * BigDecimal, BigInteger, int, long 등의 타입을 지원한다.
     * @DemicalMax(value = "$numberString") - $numberString보다 작은 값을 허용한다.
     * @DemicalMin(value = "$numberString") - $numberString보다 큰 값을 허용한다.
     * @Min(value = $number) - $number 이상의 값을 허용한다.
     * @Man(value = $number) - $number 이하의 값을 허용한다.
     *
     * 값의 범위 검증
     * BigDecimal, BigInteger, int, long 등의 타입을 지원한다.
     * @Positive - 양수를 허용한다.
     * @PositiveOrZero - 0을 포함한 양수를 허용한다.
     * @Negative - 음수를 허용한다.
     * @NegativeOrZero - 0을 포함한 음수를 허용한다.
     *
     * 시간에 대한 검증
     * Date, LocalDate, LocalDateTime 등의 타입을 지원한다.
     * @Future - 현재보다 미래의 날짜를 허용한다.
     * @FutureOrPresent - 현재를 포함한 미래의 날짜를 허용한다.
     * @Past - 현재보다 과거의 날짜를 허용한다.
     * @PastOrPresent - 현재를 포함한 과거의 날짜를 허용한다.
     *
     * 이메일 검증
     * @Email - 이메일 형식을 검사한다. ""는 허용한다.
     *
     * 자릿수 범위 검증
     * BigDecimal, BigInteger, int, long 등의 타입을 지원한다.
     * @Digits(integer = $number1, fraction = $number2) - $number1의 정수 자릿수와 $number2의 소수 자릿수를 허용한다.
     *
     * Boolean 검증
     * @AssertTrue - true인지 체크한다. null은 체크하지 않는다.
     * @AssertFalse - false인지 체크한다. null은 체크하지 않는다.
     *
     * 문자열 길이 검증
     * @Size(min = $number1, max = $number2) $number1 이상 $number2 이하의 범위를 허용한다.
     *
     * 정규식 검증
     * @Pattern(regexp = "$expression") - 정규식을 검사한다. 자바의 java.util.regex.Pattern 패키지의 컨벤션을 따른다.
     *
     */
}
