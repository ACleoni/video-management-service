package com.livingfitfamily.vms.controller;

import com.livingfitfamily.vms.model.VideoModel;
import com.livingfitfamily.vms.service.FirestoreService;
import com.livingfitfamily.vms.service.GoogleCloudStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class VideoController {
    @Autowired
    private FirestoreService firestoreService;

    @Autowired
    GoogleCloudStorageService googleCloudStorageService;

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> upload(@ModelAttribute VideoModel videoModel) {
        try {
            firestoreService.addDoc(videoModel);
            googleCloudStorageService.uploadFile(videoModel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok("Document Successfully Uploaded");
    }
}
