package com.yay.linda.pixelstarshipscrew.model;

import lombok.Data;

@Data
public class Room {
    private RoomName roomName;
    private RoomNeed roomNeed;
    private int count;
    private int crewCapacity;
}
