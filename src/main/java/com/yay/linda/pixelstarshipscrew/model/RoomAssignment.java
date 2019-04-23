package com.yay.linda.pixelstarshipscrew.model;

import lombok.Data;

import java.util.List;

@Data
public class RoomAssignment {
    private Room room;
    private List<String> crew;
}
