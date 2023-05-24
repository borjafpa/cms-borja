package com.borja.cms.application.image.dtos;

import java.util.Date;

public record UploadedImageDTO(Long id, String name, String path, Date createdAt) {
}
