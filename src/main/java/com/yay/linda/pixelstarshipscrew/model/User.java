package com.yay.linda.pixelstarshipscrew.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String username;
    private String sessionToken;
}
