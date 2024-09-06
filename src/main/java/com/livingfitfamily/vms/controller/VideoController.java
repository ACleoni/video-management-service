package com.livingfitfamily.vms.controller;

import com.livingfitfamily.vms.service.GoogleCloudStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class VideoController {
    @Autowired
    GoogleCloudStorageService googleCloudStorageService;

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> handleFileUpload(
            @RequestParam("name") String name,
            @RequestParam("category") String category,
            @RequestParam("sets") String sets,
            @RequestParam("reps") String reps,
            @RequestParam("rest") String rest,
            @RequestPart("video") MultipartFile video,
            @RequestPart("tallImage") MultipartFile tallImage,
            @RequestPart("squareImage") MultipartFile squareImage
    ) {
        try {
            String fileUrl = googleCloudStorageService.uploadFile(video, "living-fit-family-videos");
            return ResponseEntity.ok(fileUrl);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }
}
