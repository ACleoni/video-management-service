package com.livingfitfamily.vms.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoModel {
    private String name;
    private String category;
    private String sets;
    private String reps;
    private String rest;
    private MultipartFile video;
    private MultipartFile tallImage;
    private MultipartFile squareImage;
}
