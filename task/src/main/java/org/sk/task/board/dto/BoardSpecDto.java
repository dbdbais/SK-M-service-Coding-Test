package org.sk.task.board.dto;

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
    private String title;
    private String authorName;
    private String content;
    private int view;
    private LocalDateTime createdAt;
}
