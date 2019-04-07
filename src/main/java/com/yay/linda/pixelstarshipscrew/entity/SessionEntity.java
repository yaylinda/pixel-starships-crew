package com.yay.linda.pixelstarshipscrew.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.UUID;

@Data
public class SessionEntity {
    @Id
    private String id;
    private String sessionToken;
    private String username;
    private Date createdDate;
    private Boolean isActive;

    public SessionEntity(String username) {
        this.sessionToken = UUID.randomUUID().toString();
        this.username = username;
        this.createdDate = new Date();
        this.isActive = true;
    }
}
