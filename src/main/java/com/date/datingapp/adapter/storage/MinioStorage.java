package com.date.datingapp.adapter.storage;

import com.date.datingapp.infra.config.minio.MinioProperties;
import com.date.datingapp.infra.config.minio.MinioStorageError;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class MinioStorage {

    MinioClient minioClient;
    MinioProperties minioProperties;
    MinioStorageError minioStorageException;

    public String upload(UUID userId, String fileName, String contentType, byte[] content) {

        String objectName = userId + "/" + UUID.randomUUID() + "_" + fileName;

        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(minioProperties.getBucket())
                            .object(objectName)
                            .stream(new ByteArrayInputStream(content), content.length, -1)
                            .contentType(contentType)
                            .build());

            return minioProperties.getUrl() + "/" + minioProperties.getBucket() + "/" + objectName;
        } catch (Exception e) {
            throw minioStorageException.failedToUploadFile(e);
        }
    }

}
