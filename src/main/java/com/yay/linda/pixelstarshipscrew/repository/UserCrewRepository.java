package com.yay.linda.pixelstarshipscrew.repository;

import com.yay.linda.pixelstarshipscrew.entity.UserCrewEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserCrewRepository extends MongoRepository<UserCrewEntity, String> {

    List<UserCrewEntity> findByUsername(String username);
}
