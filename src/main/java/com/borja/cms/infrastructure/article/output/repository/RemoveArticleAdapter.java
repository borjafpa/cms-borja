package com.borja.cms.infrastructure.article.output.repository;

import com.borja.cms.application.article.dtos.RemovedArticleDTO;
import com.borja.cms.application.article.output.RemoveArticleRepository;
import com.borja.cms.domain.article.model.Status;
import com.borja.cms.infrastructure.article.output.mongo.ArticleMongoRepository;
import com.borja.cms.infrastructure.article.output.mongo.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Component
public class RemoveArticleAdapter implements RemoveArticleRepository {

    @Autowired
    ArticleMongoRepository articleMongoRepository;

    @Override
    public Optional<RemovedArticleDTO> removeArticle(Long articleId, String owner) {
        Article articleToRemove = new Article();
        articleToRemove.setId(articleId);
        articleToRemove.setOwner(owner);

        Optional<Article> articleOpt = articleMongoRepository.findOne(Example.of(articleToRemove));

        if (articleOpt.isPresent()) {
            Article article = articleOpt.get();
            article.setStatus(Status.REMOVED.name());
            article.setUpdatedAt(Date.from(Instant.now()));

            articleMongoRepository.save(article);

            return Optional.of(toRemovedArticleDTO(article));
        }

        return Optional.empty();
    }

    private RemovedArticleDTO toRemovedArticleDTO(Article article) {
        return new RemovedArticleDTO(article.getId(), article.getTitle(), article.getContent(),
                article.getStatus(), article.getOwner(), article.getCreatedAt(), article.getUpdatedAt());
    }
}
