package com.borja.cms.infrastructure.image.input.dtos;

import com.borja.cms.domain.image.model.Type;

public record UploadImageRequest(Long articleId, Type imageType) {
}
