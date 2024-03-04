package com.toy.knit.controller;

import com.toy.knit.service.ImageManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageManagementService imageService;

    @PostMapping("/api/image/register")
    public String imageRegister(
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            @RequestParam(value = "postId", required = false) Long postId) throws IOException {

        return imageService
                .imageUpload(imageFile, "image", postId);
    }

}
