package com.benhur.user_management.dto;

import java.time.LocalDateTime;

public class UserResponse {

    public String message;

    public Long id;

    public int status;

    public LocalDateTime timeStamp;

    public UserResponse(String message, Long id, int status){
        this.message = message;
        this.id = id;
        this.status = status;
        this.timeStamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
