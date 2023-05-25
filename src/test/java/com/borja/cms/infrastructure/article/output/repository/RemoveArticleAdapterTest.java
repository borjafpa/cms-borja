package com.borja.cms.infrastructure.article.output.repository;

import com.borja.cms.application.article.dtos.RemovedArticleDTO;
import com.borja.cms.domain.article.model.Status;
import com.borja.cms.infrastructure.article.output.mongo.ArticleMongoRepository;
import com.borja.cms.infrastructure.article.output.mongo.entity.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomUtils;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class RemoveArticleAdapterTest {

    @Mock
    private ArticleMongoRepository articleMongoRepository;

    @InjectMocks
    RemoveArticleAdapter removeArticleAdapter;

    @Test
    @DisplayName("Remove article that does not exist")
    void removeUnexistentArticle() {
        // Given
        Long articleId = RandomUtils.nextLong();
        String articleOwner = RandomStringUtils.random(10);

        // When
        Mockito.when(articleMongoRepository.findOne(any())).thenReturn(Optional.empty());

        // Then
        Optional<RemovedArticleDTO> result = removeArticleAdapter.removeArticle(articleId, articleOwner);

        assertEquals(Optional.empty(), result);
    }

    @Test
    @DisplayName("Remove article that exists")
    void removeExistentArticle() {
        // Given
        Long articleId = RandomUtils.nextLong();
        String articleOwner = RandomStringUtils.random(5);
        String title = RandomStringUtils.random(10);
        String content = RandomStringUtils.random(15);

        Date now = Date.from(Instant.now());
        Article existentArticle = new Article();
        existentArticle.setId(articleId);
        existentArticle.setOwner(articleOwner);
        existentArticle.setStatus(Status.CREATED.name());
        existentArticle.setTitle(title);
        existentArticle.setContent(content);
        existentArticle.setCreatedAt(now);
        existentArticle.setUpdatedAt(now);

        // When
        Mockito.when(articleMongoRepository.findOne(any())).thenReturn(Optional.of(existentArticle));

        // Then
        Optional<RemovedArticleDTO> resultOpt = removeArticleAdapter.removeArticle(articleId, articleOwner);

        assertTrue(resultOpt.isPresent());
        RemovedArticleDTO result = resultOpt.get();

        assertEquals(articleId, result.id());
        assertEquals(title, result.title());
        assertEquals(content, result.content());
        assertEquals(Status.REMOVED.name(), result.status());
        assertEquals(now, result.createdAt());
        assertNotEquals(now, result.updatedAt());

    }
}