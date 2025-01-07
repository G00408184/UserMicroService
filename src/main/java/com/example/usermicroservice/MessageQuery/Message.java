package com.example.usermicroservice.MessageQuery;

import lombok.Data;

@Data
public class Message {
    private String id;
    private String email;
    private String content;
    private String timestamp;
}
