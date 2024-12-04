package org.sk.task.file.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService {
    @Value("${file.upload-dir}")
    private String uploadDir;


    public String saveFile(MultipartFile file) throws IOException{
        Path directoryPath = Paths.get(uploadDir);
        if(!Files.exists(directoryPath)){
            Files.createDirectories(directoryPath);
        }

        String originalFilename = file.getOriginalFilename();
        String uniqueFilename = UUID.randomUUID()+"_"+originalFilename;

        Path filePath = directoryPath.resolve(uniqueFilename);

        // 파일 저장
        file.transferTo(filePath.toFile());

        // 저장된 파일의 경로 반환
        return filePath.toString();

    }
}
