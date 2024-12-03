package org.sk.task.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sk.task.board.entity.Board;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users",
        indexes = @Index(name = "idx_user_nickname", columnList = "user_nickname"),
        uniqueConstraints = @UniqueConstraint(name = "uc_user_nickname", columnNames = "user_nickname"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_nickname", nullable = false, unique = true)
    private String nickname;

    @Builder.Default
    @Column(name="user_email")
    private String email = null;

    @Column(name="password")
    private String password;

    @CreatedDate
    @Column(name = "user_created_at",columnDefinition = "TIMESTAMP", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "user_deleted_at",columnDefinition = "TIMESTAMP", updatable = false)
    private LocalDateTime deletedAt;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id") // 단방향 매핑
    private List<Board> writtenBoard = new ArrayList<>();
}
