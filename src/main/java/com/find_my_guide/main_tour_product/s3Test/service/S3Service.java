package com.find_my_guide.main_tour_product.s3Test.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadFile(MultipartFile file) {
        try {
            String fileName= UUID.randomUUID() + file.getOriginalFilename();
            String fileUrl= "https://" + bucket + ".s3.amazonaws.com/" + fileName;
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());
            amazonS3Client.putObject(bucket,fileName,file.getInputStream(),metadata);
            return fileUrl;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void deleteFile(String fileName) throws IOException {
        try {
            amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, fileName));
        } catch (SdkClientException e) {
            throw new IOException("Error deleteing file from S3", e);
        }
    }

}
