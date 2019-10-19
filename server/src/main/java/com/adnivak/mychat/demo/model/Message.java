package com.adnivak.mychat.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class Message {
    private String type;
    private String body;
    private String created_at;
    private String created_by;

    public Message() {
        this.created_at = new Date().toString();
    }
}
