package com.procrianca.demo.domain.repository;

import com.procrianca.demo.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ImageRepository extends JpaRepository<Image, String>, JpaSpecificationExecutor<Image> {
}
