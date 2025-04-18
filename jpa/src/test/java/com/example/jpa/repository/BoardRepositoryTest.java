package com.example.jpa.repository;

import java.util.Optional;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.Board;
import com.example.jpa.entity.Memo;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertTest() {
        LongStream.rangeClosed(1, 10).forEach(i -> {
            Board board = Board.builder()
                    .title("board Title")
                    .content("asd" + i)
                    .writer("user" + i)
                    .build();
            boardRepository.save(board);
        });
    }

    @Test
    public void updateTest() {
        Optional<Board> result = boardRepository.findById(1L);

        if (result.isPresent()) {
            Board board = result.get(); // Optional에서 실제 객체 꺼냄
            board.setWriter("수정된 텍스트입니다."); // 내용 수정
            boardRepository.save(board); // save → UPDATE 실행
            System.out.println("업데이트 완료: " + board);
        } else {
            System.out.println("해당 ID의 메모가 존재하지 않습니다.");
        }
    }

    public void readTest() {
        Optional<Board> result = boardRepository.findById(1L);

        if (result.isPresent()) {
            Board board = result.get();
            // 수정 등 처리
        }
    }

    public void listTest() {
        System.out.println("전체 메모 목록:");
        boardRepository.findAll().forEach(System.out::println);
    }

    public void deleteTest() {
        Long idToDelete = 1L;

        if (boardRepository.existsById(idToDelete)) {
            boardRepository.deleteById(idToDelete); // DELETE FROM memo WHERE id = 1
            System.out.println("삭제 완료: ID " + idToDelete);
        } else {
            System.out.println("삭제할 메모가 존재하지 않음");
        }

    }
}
