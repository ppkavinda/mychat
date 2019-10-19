package com.adnivak.mychat.demo.payload;

import com.adnivak.mychat.demo.payload.dto.ErrorResponseDTO;
import com.adnivak.mychat.demo.payload.dto.MetadataResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApiResponse<T> {
    private MetadataResponseDTO meta;
    private ErrorResponseDTO error;
    private T data;

    private static final Integer STATUS_CODE_SUCCESS = 1;
    private static final String STATUS_SUCCESS = "Success";
    private static final Integer STATUS_CODE_ERROR = 0;
    private static final String STATUS_ERROR = "Error";

    public static <T> ApiResponse<T> ok(T data, String type) {
        MetadataResponseDTO metaDTO = MetadataResponseDTO.builder()
                .message(STATUS_SUCCESS)
                .code(STATUS_CODE_SUCCESS)
                .type(type).build();
        return ApiResponse.<T>builder().data(data).meta(metaDTO).build();
    }

    public static <T> ApiResponse<T> error(String status, String title, String details) {
        ErrorResponseDTO error = ErrorResponseDTO.builder().details(details).status(status).title(title).build();
        MetadataResponseDTO meta = MetadataResponseDTO.builder().code(STATUS_CODE_ERROR).message(STATUS_ERROR).build();

        return ApiResponse.<T>builder().data(null).meta(meta).error(error).build();
    }
}
