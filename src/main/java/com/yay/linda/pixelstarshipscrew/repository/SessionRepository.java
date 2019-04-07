package com.yay.linda.pixelstarshipscrew.repository;

import com.yay.linda.pixelstarshipscrew.entity.SessionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends MongoRepository<SessionEntity, String> {


    Optional<SessionEntity> findBySessionToken(String sessionToken);

    List<SessionEntity> findByUsernameAndIsActive(String username, Boolean isActive);

    void deleteBySessionToken(String sessionToken);
}
