package com.procrianca.demo.domain.enums;

import lombok.Getter;
import org.springframework.http.MediaType;

import java.util.Arrays;

public enum ImageExtension {

    PNG(MediaType.IMAGE_PNG),
    GIF(MediaType.IMAGE_GIF),
    JPEG(MediaType.IMAGE_JPEG);

    @Getter
    private MediaType mediaType;

    ImageExtension(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public static ImageExtension getValueOfMediaType(MediaType mediaType) {
        return Arrays.stream(values())
                .filter(imageExtension -> imageExtension.mediaType.equals(mediaType))
                .findFirst()
                .orElse(null);
    }

    public static ImageExtension getValueOfExtensionName(String extension) {
        return Arrays.stream(values())
                .filter(imageExtension -> imageExtension.name().equalsIgnoreCase(extension))
                .findFirst()
                .orElse(null);
    }

}
