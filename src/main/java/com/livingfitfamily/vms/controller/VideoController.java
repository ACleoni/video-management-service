package com.livingfitfamily.vms.controller;

import com.livingfitfamily.vms.request.UploadRequest;
import com.livingfitfamily.vms.service.FirestoreService;
import com.livingfitfamily.vms.service.GoogleCloudStorageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class VideoController {
    @Autowired
    private FirestoreService firestoreService;

    @Autowired
    GoogleCloudStorageService googleCloudStorageService;

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> upload(@ModelAttribute UploadRequest uploadRequest) {

        // TODO move this somewhere else
        Map<String, String> videoMap = new HashMap<>();
        videoMap.put("name", uploadRequest.getName());
        videoMap.put("category", uploadRequest.getCategory());
        videoMap.put("sets", uploadRequest.getSets());
        videoMap.put("reps", uploadRequest.getReps());
        videoMap.put("rest", uploadRequest.getRest());
        videoMap.put("imageLink", String.format("https://livingfitfamily.b-cdn.net/tall/%s/%s",
                uploadRequest.getCategory(), uploadRequest.getTallImage().getOriginalFilename()));
        videoMap.put("squareImageLink", String.format("https://livingfitfamily.b-cdn.net/square/%s/%s",
                uploadRequest.getCategory(), uploadRequest.getSquareImage().getOriginalFilename()));
        videoMap.put("videoLink", String.format("https://livingfitfamily.b-cdn.net/%s/%s",
                uploadRequest.getCategory(), uploadRequest.getVideo().getOriginalFilename()));

        String id = StringUtils.left(uploadRequest.getVideo().getOriginalFilename(),
                Objects.requireNonNull(uploadRequest.getVideo().getOriginalFilename()).length() - 4);

        firestoreService.addVideo(id, videoMap);

        // TODO figure out how to return a JSON object
        return ResponseEntity.ok("Document Successfully Uploaded");
    }
}
