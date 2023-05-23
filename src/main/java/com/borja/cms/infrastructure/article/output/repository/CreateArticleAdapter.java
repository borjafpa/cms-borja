package com.borja.cms.infrastructure.article.output.repository;

import com.borja.cms.application.article.dtos.CreateArticleDTO;
import com.borja.cms.application.article.dtos.CreatedArticleDTO;
import com.borja.cms.application.article.output.CreateArticleRepository;
import com.borja.cms.infrastructure.article.output.mongo.ArticleMongoRepository;
import com.borja.cms.infrastructure.article.output.mongo.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class CreateArticleAdapter implements CreateArticleRepository {

    @Autowired
    ArticleMongoRepository articleMongoRepository;

    @Override
    public CreatedArticleDTO save(CreateArticleDTO createArticleDTO) {
        Article article = toArticle(createArticleDTO);

        articleMongoRepository.save(article);

        return toCreatedArticle(article);
    }

    private Article toArticle(CreateArticleDTO createArticleDTO) {
        Date now = Date.from(Instant.now());

        Article article = new Article();
        article.setId(System.currentTimeMillis());
        article.setTitle(createArticleDTO.title());
        article.setContent(createArticleDTO.content());
        article.setStatus(createArticleDTO.status());
        article.setOwner(createArticleDTO.createdBy());
        article.setCreatedAt(now);
        article.setUpdatedAt(now);

        return article;
    }

    private CreatedArticleDTO toCreatedArticle(Article article) {
        return new CreatedArticleDTO(article.getId(),
                article.getTitle(), article.getContent(),
                article.getStatus(), article.getOwner(),
                article.getCreatedAt(), article.getUpdatedAt());
    }
}
