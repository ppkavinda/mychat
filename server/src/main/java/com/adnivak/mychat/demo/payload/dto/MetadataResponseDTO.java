package com.adnivak.mychat.demo.payload.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MetadataResponseDTO {
    private Boolean success;
    private String message;
    private String type;
    private Integer code;
}
