package com.borja.cms.application.image;


import com.borja.cms.domain.image.model.Type;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface UploadImageUseCase {
    Output handle(Input input);

    record Input(MultipartFile image, Long articleId, String articleOwner, Type type) {}

    record Output(Long id, List<Long> articleIds, String name, String path, Date createdAt) {}
}
