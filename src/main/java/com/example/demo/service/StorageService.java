package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.FileData;
import com.example.demo.entity.ImageData;
import com.example.demo.repositry.FileDataRepositry;
import com.example.demo.repositry.StorageRepositry;
import com.example.demo.util.ImageUtils;

@Service
public class StorageService {

	@Autowired
	private StorageRepositry repositry;
	@Autowired
	private FileDataRepositry fileRepositry;
	
	private final 	String Folder_path="C:\\Users\\GoBrilliant Sanjeev\\Desktop\\MyFile";
	

	
	public String uploadImage(MultipartFile file) throws Exception{
		ImageData imageDate = repositry.save(ImageData.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.imageData(ImageUtils.compareImage(file.getBytes())).build());
		if(imageDate!=null) {
			return "file uploand sicessful"+file.getOriginalFilename();
		}
		return null;
	}
	
	public byte[] downloandImage(String fileName) {
		                  Optional<ImageData> dbImageData = repositry.findByName(fileName);
		                 byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
		                 return images;
	}
	
	public String uploadImageToFileSystem(MultipartFile file) throws Exception{
		String filepath=Folder_path+file.getOriginalFilename();
		
		FileData fileData = fileRepositry.save(FileData.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.filePath(filepath).build());
		file.transferTo(new File(filepath));
				
		
		if(fileData!=null) {
			return "file uploand sicessful"+file.getOriginalFilename();
		}
		return null;
	}
	public byte[] downloandImageFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileRepositry.findByName(fileName);
        String filePath=fileData.get().getFilePath();
       byte[] images = Files.readAllBytes(new File(filePath).toPath());
       return images;
}
	
}
