package com.adnivak.mychat.demo.payload.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDTO {
    private String message;
}
