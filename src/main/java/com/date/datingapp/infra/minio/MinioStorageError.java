package com.date.datingapp.infra.minio;


import com.date.datingapp.shared.exception.CodedException;
import org.springframework.stereotype.Component;

@Component
public class MinioStorageError {

    public static final String FAILED_TO_UPLOAD_FILE = "0188ca11-001";

    public CodedException failedToUploadFile(Throwable cause) {
        var message = "Failed to upload file to MinIO";
        return new CodedException(FAILED_TO_UPLOAD_FILE, message, cause);
    }
}
