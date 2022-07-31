package com.board.service;

import com.board.dto.BoardDto;
import com.board.dto.SearchDto;
import com.board.entity.Board;
import com.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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

    public void update(BoardDto boardDto){
        Board board = boardRepository.findById(boardDto.getId()).orElseThrow(EntityNotFoundException::new);
        board.update(boardDto);
    }

    public void delete(Long boardId){
        boardRepository.deleteById(boardId);
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

    @Transactional(readOnly = true)
    public Page<Board> list(Pageable pageable, SearchDto searchDto){
        if(searchDto.getSearch() != null && searchDto.getSearch() != null){
            if(searchDto.getSelect().equals("title")){
                return boardRepository.findByTitleContaining(searchDto.getSearch(), pageable);
            }else if(searchDto.getSelect().equals("createdBy")){
                return boardRepository.findByCreatedByContaining(searchDto.getSearch(), pageable);
            }
        }

        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Board> search(Pageable pageable, String search, String select) {
        if (select.equals("title")) {
            return boardRepository.findByTitleContaining(search, pageable);
        }
        return boardRepository.findByCreatedByContaining(search, pageable);
    }



}
