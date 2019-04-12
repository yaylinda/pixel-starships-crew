package com.yay.linda.pixelstarshipscrew.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserCrewEntity {
    @Id
    private String id;
    private String username;
    private Integer crewId;

    public UserCrewEntity(String username, Integer crewId) {
        this.username = username;
        this.crewId = crewId;
    }
}
