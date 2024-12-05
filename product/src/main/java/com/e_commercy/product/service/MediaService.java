package com.e_commercy.product.service;

import com.e_commercy.product.model.Media;
import com.e_commercy.product.repository.MediaRepository;
import com.e_commercy.product.viewmodel.media.MediaVm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MediaService {
    private final MediaRepository mediaRepository;

    public MediaVm uploadMedia(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePath = "/path/to/save/media/" + fileName;
        File destFile = new File(filePath);
        file.transferTo(destFile);
        Media media = Media.builder()
                .fileName(fileName)
                .filePath(filePath)
                .fileType(file.getContentType())
                .build();
        Media savedMedia = mediaRepository.save(media);
        return MediaVm.fromModel(savedMedia);
    }
}
