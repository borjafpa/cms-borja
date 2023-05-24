package com.borja.cms.infrastructure.image.output.repository;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.borja.cms.application.image.dtos.UploadImageDTO;
import com.borja.cms.application.image.dtos.UploadedImageDTO;
import com.borja.cms.application.image.output.UploadImageRepository;
import com.borja.cms.domain.image.model.Type;
import com.borja.cms.infrastructure.image.output.aws.AmazonS3Service;
import com.borja.cms.infrastructure.image.output.mongo.ImageMongoRepository;
import com.borja.cms.infrastructure.image.output.mongo.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.*;

@Component
public class UploadImageAdapter implements UploadImageRepository {

    @Autowired
    private AmazonS3Service amazonS3Service;

    @Autowired
    private ImageMongoRepository imageMongoRepository;

    @Value("${aws.s3.bucket_name}")
    private String bucketName;

    @Override
    public UploadedImageDTO upload(UploadImageDTO uploadImageDTO) {
        try {
            return handleUpload(uploadImageDTO);
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private UploadedImageDTO handleUpload(UploadImageDTO uploadImageDTO) throws IOException, NoSuchAlgorithmException {

        MultipartFile file = uploadImageDTO.file();

        if (file.isEmpty())
            throw new IllegalStateException("Cannot upload empty file");

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String fileName = String.format("%s", getFileName(file));
        String path = String.format("%s/%s/%s/%s", bucketName,
                fileName.substring(0 ,1), fileName.substring(1, 2), fileName.substring(2, 3));


        // Uploading file to s3
        PutObjectResult putObjectResult = amazonS3Service.upload(
                path, fileName, Optional.of(metadata), file.getInputStream());

        Image image = toImage(fileName, path, uploadImageDTO.imageType(), uploadImageDTO.articleId());
        imageMongoRepository.save(image);

        return new UploadedImageDTO(image.getId(), image.getName(), image.getPath(), image.getCreatedAt());
    }

    private String getFileName(MultipartFile file) throws IOException, NoSuchAlgorithmException {
        byte[] hash = MessageDigest.getInstance("MD5").digest(file.getBytes());
        return new BigInteger(1, hash).toString(16);
    }

    private Image toImage(String name, String path, Type type, Long articleId) {
        Date now = Date.from(Instant.now());

        Image image = new Image();
        image.setId(System.currentTimeMillis());
        image.setName(name);
        image.setPath(path);
        image.setType(type.name());
        image.setArticleIds(List.of(articleId));
        image.setCreatedAt(now);
        image.setUpdatedAt(now);

        return image;
    }




}
