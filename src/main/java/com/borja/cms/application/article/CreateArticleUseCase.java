package com.borja.cms.application.article;


import com.borja.cms.domain.article.model.Status;

import java.util.Date;

public interface CreateArticleUseCase {
    Output handle(Input input);

    record Input(String title, String content, String owner) {}

    record Output(Long id, String title, String content, Status status, Date createdAt) {}
}
