package com.example.recommendation.exception;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ErrorResponse {
    private List<String> errors;
    private Meta meta;

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    public static class Meta {
        private String result;
        private int code;
    }

    public static ErrorResponse from(List<FieldError> fieldErrors) {
        List<String> errorMessages = fieldErrors.stream()
                .map(fieldError -> fieldError.getDefaultMessage() + " 요청 정보 : (" + fieldError.getRejectedValue() + ")")
                .collect(Collectors.toList());

        return ErrorResponse.builder()
                .errors(errorMessages)
                .meta(Meta.builder()
                        .code(-1)
                        .result("fail")
                        .build())
                .build();
    }

}

