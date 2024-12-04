package org.sk.task.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardRegisterDto {
    private String title;
    private String content;

    @Override
    public String toString() {
        return "BoardRegisterDto{" +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
