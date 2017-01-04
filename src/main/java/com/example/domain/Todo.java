package com.example.domain;

import lombok.Data;

@Data
public class Todo {
    private int id;
    private String title;
    private String details;
    private boolean finished;
}
