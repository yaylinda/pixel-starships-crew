package com.yay.linda.pixelstarshipscrew.api;

import com.yay.linda.pixelstarshipscrew.model.Room;
import com.yay.linda.pixelstarshipscrew.model.RoomAssignment;
import com.yay.linda.pixelstarshipscrew.model.UserCrew;
import com.yay.linda.pixelstarshipscrew.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@CrossOrigin
public class RoomController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    private RoomService roomService;

    @GetMapping("")
    public ResponseEntity<List<Room>> getRoomsForUser(
            @RequestHeader("Session-Token") String sessionToken) {
        LOGGER.info("GET - rooms for user");
        return ResponseEntity.ok(roomService.getRoomsForUser(sessionToken));
    }

    @PostMapping("")
    public ResponseEntity<List<Room>> addRoomForUser(
            @RequestHeader("Session-Token") String sessionToken,
            @RequestBody List<Room> roomsToAdd) {
        LOGGER.info("POST - room for user");
        return ResponseEntity.ok(roomService.addRoomForUser(sessionToken, roomsToAdd));
    }

    @GetMapping("/assignment")
    public ResponseEntity<List<RoomAssignment>> assignCrewToRooms(
            @RequestHeader("Session-Token") String sessionToken) {
        LOGGER.info("GET - assign crew to rooms");
        return ResponseEntity.ok(roomService.assignCrewToRooms(sessionToken));
    }
}
