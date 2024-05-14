package com.example.demo.repositry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ImageData;

public interface StorageRepositry extends JpaRepository<ImageData, Long>{

    Optional<ImageData>	findByName(String name);
}
