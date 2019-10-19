package com.adnivak.mychat.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, precision = 10)
    private int id;

    @Column(nullable = false, length = 255)
    private String body;

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
