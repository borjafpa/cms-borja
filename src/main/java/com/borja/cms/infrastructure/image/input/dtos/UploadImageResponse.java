package com.borja.cms.infrastructure.image.input.dtos;

import java.util.Date;

public record UploadImageResponse(Long imageId, String name, String path, Date createdAt) {
}
