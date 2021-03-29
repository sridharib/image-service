package com.imageservice.api;

import com.imageservice.service.ImageService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class ImageServiceAPI {

    private ImageService imageService;

    public ImageServiceAPI(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(path = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getImage() throws IOException {
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(imageService.getImageAsStream()));
    }

}
