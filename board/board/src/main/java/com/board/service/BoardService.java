package com.board.service;

import com.board.dto.BoardDto;
import com.board.entity.Board;
import com.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void save(BoardDto boardDto){
        Board board = new Board(boardDto);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public BoardDto read(Long boardId){
        Board board = boardRepository.findById(boardId).orElseThrow(EntityNotFoundException::new);
        BoardDto boardDto = new BoardDto(board);
        boardRepository.increaseHits(boardId);
        boardDto.setContent(boardDto.getContent().replace(" ", "&nbsp;").replace("\n", "<br>"));
        return boardDto;
    }

    @Transactional(readOnly = true)
    public List<Board> list(){
        return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
}
