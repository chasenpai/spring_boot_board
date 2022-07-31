package com.board.service;

import com.board.dto.BoardDto;
import com.board.entity.Board;
import com.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BoardServiceTest {


    @Autowired
    private BoardRepository boardRepository;

    @Test
    @DisplayName("게시글 샘플")
    void save(){
        BoardDto boardDto = new BoardDto();
        for(int i = 1; i <= 100; i++){
            boardDto.setTitle("게시글 샘플 " + i);
            boardDto.setContent(
                    "게시글 샘플 내용 게시글 샘플 내용 게시글 샘플 내용 게시글 샘플 내용 게시글 샘플 내용 게시글 샘플 내용 게시글 샘플 내용 " +
                    "게시글 샘플 내용 게시글 샘플 내용 게시글 샘플 내용 게시글 샘플 내용 게시글 샘플 내용 게시글 샘플 내용 게시글 샘플 내용 " +
                    "게시글 샘플 내용 게시글 샘플 내용 게시글 샘플 내용 게시글 샘플 내용 게시글 샘플 내용 게시글 샘플 내용 게시글 샘플 내용 " +
                    "게시글 샘플 내용 게시글 샘플 내용 게시글 샘플 내용 게시글 샘플 내용 게시글 샘플 내용 게시글 샘플 내용 게시글 샘플 내용 " +
                    "게시글 샘플 내용 게시글 샘플 내용 게시글 샘플 내용 게시글 샘플 내용 게시글 샘플 내용 게시글 샘플 내용 게시글 샘플 내용 ");
            boardDto.setCreatedBy("작성자 " + i);
            boardDto.setLastModifiedBy("작성자 " + i);
            boardDto.setCreatedDate(LocalDateTime.now());
            boardDto.setLastModifiedDate(LocalDateTime.now());

            Board board = new Board(boardDto);
            boardRepository.save(board);
        }
    }
}
