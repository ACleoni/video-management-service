package com.livingfitfamily.video_management_service.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class VideoController {
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
        // Save the file to the server or process it as needed
        String message = "File uploaded successfully";
        return ResponseEntity.ok(message);
    }
}
