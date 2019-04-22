package com.yay.linda.pixelstarshipscrew.repository;

import com.yay.linda.pixelstarshipscrew.entity.UserRoomEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoomRepository extends MongoRepository<UserRoomEntity, String> {
}
