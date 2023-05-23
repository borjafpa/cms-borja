package com.borja.cms.infrastructure.user.output.mongo;

import com.borja.cms.infrastructure.user.output.mongo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserMongoRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
