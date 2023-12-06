package com.procrianca.demo.service.impl;

import com.procrianca.demo.domain.entity.Image;
import com.procrianca.demo.domain.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;


    @Transactional
    public Image save(Image image) {
        var imageSaved = this.imageRepository.save(image);
        return imageSaved;
    }

    public Optional<Image> findById(String id) {
        return this.imageRepository.findById(id);
    }


}
