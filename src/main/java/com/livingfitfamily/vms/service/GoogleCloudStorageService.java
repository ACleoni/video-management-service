package com.livingfitfamily.vms.service;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class GoogleCloudStorageService {
    private final Storage storage = StorageOptions.getDefaultInstance().getService();

    public String uploadFile(MultipartFile file, String bucketName) throws IOException {
        BlobInfo blobInfo = storage.create(
                BlobInfo.newBuilder(bucketName, Objects.requireNonNull(file.getOriginalFilename())).build(),
                file.getInputStream()
        );
        return blobInfo.getMediaLink();
    }
}
