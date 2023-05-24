package com.borja.cms.infrastructure.image.output.mongo;

import com.borja.cms.infrastructure.image.output.mongo.entity.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageMongoRepository extends MongoRepository<Image, String> {

}
