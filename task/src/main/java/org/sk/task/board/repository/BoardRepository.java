package org.sk.task.board.repository;

import org.sk.task.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
    // createdAt 기준 내림차순 정렬
    Page<Board> findAllByOrderByCreatedAtDesc(Pageable pageable);

    // createdAt 기준 오름차순 정렬
    Page<Board> findAllByOrderByCreatedAtAsc(Pageable pageable);


}
