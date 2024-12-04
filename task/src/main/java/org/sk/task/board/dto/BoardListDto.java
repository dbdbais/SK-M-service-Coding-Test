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
public class BoardListDto {

    @Schema(description = "게시글 제목", example = "Spring Boot 소개")
    private String title;

    @Schema(description = "작성자 이름", example = "홍길동")
    private String authorName;

    @Schema(description = "조회수", example = "120")
    private int view;

    @Schema(description = "게시글에 첨부파일이 있는지 여부", example = "true")
    private boolean isFile;

    @Schema(description = "게시글 등록 날짜와 시간", example = "2024-12-01T10:00:00")
    private LocalDateTime registerDate;
}
