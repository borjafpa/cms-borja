package com.borja.cms.application.image.dtos;

import com.borja.cms.domain.image.model.Type;
import org.springframework.web.multipart.MultipartFile;

public record UploadImageDTO(MultipartFile file, String articleOwner, Long articleId, Type imageType) {
}
