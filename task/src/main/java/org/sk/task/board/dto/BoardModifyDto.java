package org.sk.task.board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardModifyDto {

    @Schema(description = "수정할 게시글의 고유 식별자", example = "1")
    private Long boardId;

    @Schema(description = "게시글의 새 제목", example = "업데이트된 Spring Boot 튜토리얼")
    private String title;

    @Schema(description = "게시글의 새 내용", example = "이번 튜토리얼은 이제 Spring Security와 같은 고급 주제를 포함합니다.")
    private String content;
}
