package com.livingfitfamily.vms.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {
    @Value("${spring.cloud.gcp.credentials.location}")
    private String serviceAccountKey;

    @Bean
    FirebaseApp initialize() throws IOException {
        // Use a service account
        FileInputStream serviceAccount = new FileInputStream(serviceAccountKey);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://living-fit-family-32db6-default-rtdb.firebaseio.com")
                .build();

        return FirebaseApp.initializeApp(options);
    }

//    @Bean
//    public Firestore getFirestore() {
//        return FirestoreClient.getFirestore();
//    }
}
