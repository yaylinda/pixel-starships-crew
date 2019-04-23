package com.yay.linda.pixelstarshipscrew.repository;

import com.yay.linda.pixelstarshipscrew.entity.UserRoomEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoomRepository extends MongoRepository<UserRoomEntity, String> {

    List<UserRoomEntity> findAllByUsername(String username);
}
