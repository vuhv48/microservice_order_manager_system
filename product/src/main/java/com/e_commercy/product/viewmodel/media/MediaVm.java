package com.e_commercy.product.viewmodel.media;

import com.e_commercy.product.model.Media;
import lombok.Builder;

@Builder
public record MediaVm(
        Long id,
        String fileName,
        String fileType,
        String filePath
) {
    public static MediaVm fromModel(Media media){
        return new MediaVm(
                media.getId(),
                media.getFileName(),
                media.getFileType(),
                media.getFilePath()
        );
    }
}
