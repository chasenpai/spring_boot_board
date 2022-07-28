package com.board.entity;

import com.board.dto.BoardDto;
import com.board.dto.MemberDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "board")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    private int hits;

    public Board(BoardDto boardDto){
        this.id = boardDto.getId();
        this.title = boardDto.getTitle();
        this.content = boardDto.getContent();
    }





}
