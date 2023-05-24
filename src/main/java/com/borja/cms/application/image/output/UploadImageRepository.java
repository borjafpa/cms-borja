package com.borja.cms.application.image.output;

import com.borja.cms.application.article.dtos.CreateArticleDTO;
import com.borja.cms.application.article.dtos.CreatedArticleDTO;
import com.borja.cms.application.image.dtos.UploadImageDTO;
import com.borja.cms.application.image.dtos.UploadedImageDTO;

public interface UploadImageRepository {
    UploadedImageDTO upload(UploadImageDTO uploadImageDTO);
}
