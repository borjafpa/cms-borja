package com.borja.cms.application.image.service;

import com.borja.cms.application.image.UploadImageUseCase;
import com.borja.cms.application.image.dtos.UploadImageDTO;
import com.borja.cms.application.image.dtos.UploadedImageDTO;
import com.borja.cms.application.image.output.UploadImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadImageService implements UploadImageUseCase {

    @Autowired
    UploadImageRepository uploadImageRepository;

    @Override
    public Output handle(Input input) {
        UploadedImageDTO uploadedImageDTO = uploadImageRepository.upload(toUploadImageDTO(input));

        return new Output(uploadedImageDTO.id(), List.of(input.articleId()),
                uploadedImageDTO.name(), uploadedImageDTO.path(), uploadedImageDTO.createdAt());
    }

    private UploadImageDTO toUploadImageDTO(Input input) {
        return new UploadImageDTO(input.image(), input.articleOwner(), input.articleId(), input.type());
    }

}
