package com.example.recommendation.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Builder
@Getter
public class CreateRecommendationRequest {
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;
    @ValueOfEnum(enumClass = GenderType.class, ignoreCase = true, message = "성별을 선택해 주세요.")
    private String gender;
    @Email(message = "유효하지 않은 이메일 주소입니다.")
    private String email;

    public enum GenderType {
        MALE,
        FEMALE,
        UNCHECKED;
    }
}
