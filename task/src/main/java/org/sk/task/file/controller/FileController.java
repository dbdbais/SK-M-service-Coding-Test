package org.sk.task.file.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@Slf4j
@RequestMapping("/api/v1/files")
public class FileController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Operation(summary = "파일 다운로드", description = "서버에 저장된 파일을 다운로드하는 API입니다. 파일을 제공하려면 파일 이름을 입력하세요.")
    @GetMapping("/{filename}")
    public ResponseEntity<Resource> downloadFile(
            @Parameter(description = "다운로드할 파일의 이름", example = "example.txt") @PathVariable String filename) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(filename);

            log.info("File path: {}", filePath.toString());

            Resource resource = new UrlResource(filePath.toUri());

            log.info("File exists: {}", resource.exists());
            log.info("File readable: {}", Files.isReadable(filePath));

            if (resource.exists() && Files.isReadable(filePath)) {
                // 다운로드를 위한 헤더 추가
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                        .body(resource);  // 직접 resource를 body로 전달
            } else {
                log.info("BAD");
                return ResponseEntity.badRequest().build();  // 파일이 없거나 읽을 수 없는 경우
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // 예외 처리
        }
    }
}
