package com.adnivak.mychat.demo.payload.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponseDTO {
    private String status;
    private String title;
    private String details;
}
