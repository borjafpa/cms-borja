package com.borja.cms.infrastructure.article.input.dtos;

import com.borja.cms.domain.article.model.Status;

import java.util.Date;

public record CreateArticleResponse(Long id, String title, String content, Status status, Date createdAt) {
}
