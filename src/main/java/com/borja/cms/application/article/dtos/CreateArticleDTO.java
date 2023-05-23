package com.borja.cms.application.article.dtos;

public record CreateArticleDTO(String title, String content, String status, String createdBy) {
}
