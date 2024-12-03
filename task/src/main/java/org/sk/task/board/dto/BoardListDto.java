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
public class BoardListDto {
    private String title;
    private String authorName;
    private int view;
    private boolean isFile;
    private LocalDateTime registerDate;
}
