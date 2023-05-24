package com.borja.cms.infrastructure.image.input;

import com.borja.cms.application.article.CreateArticleUseCase;
import com.borja.cms.application.article.RemoveArticleUseCase;
import com.borja.cms.application.image.UploadImageUseCase;
import com.borja.cms.domain.image.model.Type;
import com.borja.cms.infrastructure.article.input.dtos.CreateArticleRequest;
import com.borja.cms.infrastructure.article.input.dtos.CreateArticleResponse;
import com.borja.cms.infrastructure.image.input.dtos.UploadImageRequest;
import com.borja.cms.infrastructure.image.input.dtos.UploadImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value = ImageController.BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class ImageController {

    public static final String BASE_URL = "/image/v1/";

    @Autowired
    UploadImageUseCase uploadImageUseCase;

    @PostMapping("upload/{articleId}/{type}")
    public ResponseEntity<UploadImageResponse> uploadImage(@RequestParam("file") MultipartFile image,
                                                           @PathVariable("articleId") Long articleId,
                                                           @PathVariable("type") String imageType,
                                                           Authentication authentication) {
        UploadImageUseCase.Output output = uploadImageUseCase.handle(
                new UploadImageUseCase.Input(image, articleId, authentication.getName(), Type.valueOf(imageType))
        );

        return ResponseEntity.ok()
                .body(new UploadImageResponse(output.id(), output.name(), output.path(), output.createdAt()));
    }

    @PostMapping("other-upload")
    public ResponseEntity uploadImage(@RequestParam("file") MultipartFile image, Authentication authentication) {

        return ResponseEntity.ok().build();
    }

}
