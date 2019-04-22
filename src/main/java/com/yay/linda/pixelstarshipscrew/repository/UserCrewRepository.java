package com.yay.linda.pixelstarshipscrew.repository;

import com.yay.linda.pixelstarshipscrew.entity.UserCrewEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCrewRepository extends MongoRepository<UserCrewEntity, String> {

    List<UserCrewEntity> findByUsername(String username);
}
