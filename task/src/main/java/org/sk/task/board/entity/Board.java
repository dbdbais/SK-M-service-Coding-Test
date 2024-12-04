package org.sk.task.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sk.task.user.entity.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Entity
@Builder
@Slf4j
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name="board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    @Column(name = "board_title")
    private String title;

    @Column(name="author_id")
    private Long authorId;

    @Column(name="author_name")
    private String authorName;

    @Column(name="board_content")
    private String content;

    @Builder.Default
    @Column(name="board_view")
    private int view = 0;

    @Builder.Default
    @Column(name = "file_path")
    private String filePath = null; // 파일 경로만 저장

    @CreatedDate
    @Column(name = "board_created_at",columnDefinition = "TIMESTAMP", updatable = false)
    private LocalDateTime createdAt;


    public void increase(){
        this.view++;
        log.info(boardId+"번 게시글의 조회수가 증가하였습니다.");
    }

    public void changeTitle(String newTitle){
        this.title = newTitle;
    }

    public void changeContent(String newContent){
        this.content = newContent;
    }

    public void changeFilePath(String newFilePath){
        this.filePath = newFilePath;
    }


}
