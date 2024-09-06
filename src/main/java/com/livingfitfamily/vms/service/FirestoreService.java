package com.livingfitfamily.vms.service;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FirestoreService {
//    @Autowired
//    Firestore firestore;

    public void addVideo(String userId, Map<String, String> video) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("videos").document(userId);
        docRef.set(video);
    }
}
