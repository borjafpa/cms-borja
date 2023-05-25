package com.borja.cms.infrastructure.article.output.mongo;

import com.borja.cms.infrastructure.article.output.mongo.entity.Article;
import com.borja.cms.infrastructure.user.output.mongo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ArticleMongoRepository extends MongoRepository<Article, String> {
}
