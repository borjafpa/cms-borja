package com.borja.cms.application.article.output;

import com.borja.cms.application.article.dtos.RemovedArticleDTO;

import java.util.Optional;

public interface RemoveArticleRepository {
    Optional<RemovedArticleDTO> removeArticle(Long articleId, String owner);
}
