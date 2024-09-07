package com.livingfitfamily.vms.service;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.livingfitfamily.vms.model.VideoModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class FirestoreService {
    @Autowired
    Firestore firestore;

    public void addDoc(VideoModel videoModel) {
        String id = StringUtils.left(videoModel.getVideo().getOriginalFilename(),
                Objects.requireNonNull(videoModel.getVideo().getOriginalFilename()).length() - 4);

        Map<String, String> doc = generateDoc(videoModel);

        DocumentReference docRef = firestore.collection("videos").document(Objects.requireNonNull(id));
        docRef.set(doc);
    }

    private Map<String, String> generateDoc(VideoModel videoModel) {
        Map<String, String> doc = new HashMap<>();

        doc.put("name", videoModel.getName());
        doc.put("category", videoModel.getCategory());
        doc.put("sets", videoModel.getSets());
        doc.put("reps", videoModel.getReps());
        doc.put("rest", videoModel.getRest());
        doc.put("imageLink", String.format("https://livingfitfamily.b-cdn.net/tall/%s/%s",
                videoModel.getCategory(), videoModel.getTallImage().getOriginalFilename()));
        doc.put("squareImageLink", String.format("https://livingfitfamily.b-cdn.net/square/%s/%s",
                videoModel.getCategory(), videoModel.getSquareImage().getOriginalFilename()));
        doc.put("videoLink", String.format("https://livingfitfamily.b-cdn.net/%s/%s",
                videoModel.getCategory(), videoModel.getVideo().getOriginalFilename()));

        return doc;
    }

}
