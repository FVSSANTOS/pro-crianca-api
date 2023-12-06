package com.procrianca.demo.domain.mapper;

import com.procrianca.demo.domain.dtos.ImageDTO;
import com.procrianca.demo.domain.entity.Image;
import com.procrianca.demo.domain.enums.ImageExtension;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class ImageMapper {

    public Image mapToImage(MultipartFile file, String name) throws IOException {

        var photoExtension = ImageExtension.getValueOfMediaType(MediaType.parseMediaType(file.getContentType()));

        return Image.builder()
                .name(name)
                .size(file.getSize())
                .extension(photoExtension)
                .fileImage(file.getBytes())
                .build();
    }

    public ImageDTO imageToDTO(Image image, String url) {
        return ImageDTO.builder()
                .url(url)
                .name(image.getName())
                .extension(image.getExtension().name())
                .size(image.getSize())
                .uploadDate(image.getUploadDate().toLocalDate())
                .build();
    }

}
