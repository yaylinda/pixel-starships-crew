package com.yay.linda.pixelstarshipscrew.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserCrewEntity {
    @Id
    private String id;
    private String username;
    private String crewName;
    private List<String> crewItems;

    public UserCrewEntity(String username, String crewName) {
        this.username = username;
        this.crewName = crewName;
        this.crewItems = new ArrayList<>();
    }
}
