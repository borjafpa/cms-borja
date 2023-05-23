package com.borja.cms.application.article.output;

import com.borja.cms.application.article.dtos.CreateArticleDTO;
import com.borja.cms.application.article.dtos.CreatedArticleDTO;

public interface CreateArticleRepository {
    CreatedArticleDTO save(CreateArticleDTO createArticleDTO);
}
