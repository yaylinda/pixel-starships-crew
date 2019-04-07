package com.yay.linda.pixelstarshipscrew.entity;

import com.yay.linda.pixelstarshipscrew.model.RegisterRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private Date createdDate;
    private Date lastActiveDate;

    public UserEntity(RegisterRequest registerRequest) {
        this.username = registerRequest.getUsername();
        this.password = registerRequest.getPassword();
        this.email = registerRequest.getEmail();
        this.createdDate = new Date();
        this.lastActiveDate = new Date();
    }
}
