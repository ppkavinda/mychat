package com.adnivak.mychat.demo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Subscription {
    private String username;
}
