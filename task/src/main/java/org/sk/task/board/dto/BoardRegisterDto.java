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
public class BoardRegisterDto {

    @Schema(description = "게시글의 제목", example = "Spring Boot 기초")
    private String title;

    @Schema(description = "게시글의 내용", example = "이 글은 Spring Boot 프레임워크의 기초에 대한 자세한 설명입니다.")
    private String content;

    @Override
    public String toString() {
        return "BoardRegisterDto{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
