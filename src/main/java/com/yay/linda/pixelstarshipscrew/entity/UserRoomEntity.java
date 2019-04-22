package com.yay.linda.pixelstarshipscrew.entity;

import com.yay.linda.pixelstarshipscrew.model.Room;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserRoomEntity {
    @Id
    private String id;
    private String username;
    private Room room;

    public UserRoomEntity(String username, Room room) {
        this.username = username;
        this.room = room;
    }
}
