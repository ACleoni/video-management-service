package com.livingfitfamily.vms.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class GoogleCloudStorageConfig {
    @Value("${spring.cloud.gcp.credentials.cloud.storage.location}")
    private String serviceAccountKey;

    @Bean
    public Storage storage() throws IOException {
        FileInputStream serviceAccount = new FileInputStream(serviceAccountKey);
        return StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build()
                .getService();
    }
}


