package org.sk.task.board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardSpecDto {

    @Schema(description = "게시글의 제목", example = "Spring Boot 기초")
    private String title;

    @Schema(description = "게시글 작성자의 이름", example = "Kangwoo Lee")
    private String authorName;

    @Schema(description = "게시글의 내용", example = "이 글은 Spring Boot 기초에 대한 자세한 설명입니다.")
    private String content;

    @Schema(description = "게시글의 조회수", example = "120")
    private int view;

    @Schema(description = "파일 경로", example = "/src/a.png")
    private String filePath;

    @Schema(description = "게시글이 작성된 날짜와 시간", example = "2024-12-01T10:00:00")
    private LocalDateTime createdAt;
}
