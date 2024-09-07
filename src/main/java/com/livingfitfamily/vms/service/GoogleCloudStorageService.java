package com.livingfitfamily.vms.service;

import com.google.cloud.storage.*;
import com.livingfitfamily.vms.model.VideoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public class GoogleCloudStorageService {
    @Autowired
    private Storage storage;

    @Value("${spring.cloud.gcp.storage.bucketName}")
    private String bucketName;

    public void uploadFile(VideoModel videoModel) throws IOException {
        MultipartFile video = videoModel.getVideo();
        BlobId blobId = BlobId.of(bucketName, videoModel.getCategory() + "/" + video.getOriginalFilename());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType("application/mp4")
                .build();
        storage.create(blobInfo, video.getBytes());
    }
}
