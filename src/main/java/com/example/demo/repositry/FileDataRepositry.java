package com.example.demo.repositry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.FileData;

public interface FileDataRepositry extends JpaRepository<FileData, Long> {

	Optional<FileData> findByName(String fileName);

}
