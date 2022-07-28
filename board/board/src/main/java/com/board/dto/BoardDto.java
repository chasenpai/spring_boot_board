package com.board.dto;

import com.board.entity.Board;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardDto {

    private Long id;

    private String title;

    private String content;

    private int hits;

    private String createdBy;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    public BoardDto(Board board){
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.hits = board.getHits();
        this.createdBy = board.getCreatedBy();
        this.createdDate = board.getCreatedDate();
        this.lastModifiedDate = board.getLastModifiedDate();
    }

}
