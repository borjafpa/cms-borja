package com.borja.cms.application.article.dtos;

import java.util.Date;

public record CreatedArticleDTO(Long id, String title, String content, String status, String owner, Date createdAt, Date updatedAt) {
}
