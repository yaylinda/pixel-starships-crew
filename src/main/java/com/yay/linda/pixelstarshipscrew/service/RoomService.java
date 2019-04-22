package com.yay.linda.pixelstarshipscrew.service;

import com.yay.linda.pixelstarshipscrew.entity.UserRoomEntity;
import com.yay.linda.pixelstarshipscrew.model.Room;
import com.yay.linda.pixelstarshipscrew.model.User;
import com.yay.linda.pixelstarshipscrew.repository.UserRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoomRepository userRoomRepository;

    public List<Room> addRoomForUser(String sessionToken, List<Room> roomsToAdd) {
        User user = userService.getUserFromSessionToken(sessionToken);

        roomsToAdd.forEach(r -> userRoomRepository.save(new UserRoomEntity(user.getUsername(), r)));

        return roomsToAdd;
    }
}
