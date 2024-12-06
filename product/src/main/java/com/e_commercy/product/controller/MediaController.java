package com.e_commercy.product.controller;

import com.e_commercy.product.service.MediaService;
import com.e_commercy.product.viewmodel.media.MediaVm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @PostMapping("/backoffice/upload")
    public ResponseEntity<MediaVm> uploadMedia(@RequestParam("file") MultipartFile file) {
        MediaVm mediaVm = null;
        try {
            mediaVm = mediaService.uploadMedia(file);
            return ResponseEntity.ok(mediaVm);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
