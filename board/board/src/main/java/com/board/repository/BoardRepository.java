package com.board.repository;

import com.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Modifying
    @Query("update Board b set b.hits = b.hits + 1 where b.id =:boardId")
    int increaseHits(Long boardId);

    Page<Board> findAll(Pageable pageable);

    Page<Board> findByTitleContaining(String search, Pageable pageable);

    Page<Board> findByCreatedByContaining(String search, Pageable pageable);

}
