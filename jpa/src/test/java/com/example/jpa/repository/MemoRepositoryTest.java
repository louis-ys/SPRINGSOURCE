package com.example.jpa.repository;

import java.util.Optional;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.Memo;

@SpringBootTest // Spring 환경에서 테스트 실행 (Bean 주입 가능)
public class MemoRepositoryTest {

    @Autowired // MemoRepository 자동 주입 (스프링이 알아서 연결해줌)
    private MemoRepository memoRepository;

    /**
     * 1. INSERT 테스트
     * - 1부터 10까지 숫자를 반복하면서
     * - memoText 값이 각각 다른 Memo 객체를 생성
     * - DB에 저장 (save 호출)
     */
    @Test
    public void insertTest() {
        LongStream.rangeClosed(1, 10).forEach(i -> {
            Memo memo = Memo.builder()
                    .memoText("memoText" + i) // 예: memoText1, memoText2 ...
                    .build();

            memoRepository.save(memo); // JPA가 INSERT SQL 실행
        });
    }

    /**
     * 2. UPDATE 테스트
     * - ID가 1인 Memo를 먼저 조회 (있어야 수정 가능)
     * - 조회된 메모가 존재하면 memoText 값을 새 값으로 변경
     * - 다시 save 하면 내부적으로 UPDATE 실행됨
     */
    @Test
    public void updateTest() {
        Optional<Memo> result = memoRepository.findById(1L); // 1번 메모 찾기

        if (result.isPresent()) {
            Memo memo = result.get(); // Optional에서 실제 객체 꺼냄
            memo.setMemoText("수정된 텍스트입니다."); // 내용 수정
            memoRepository.save(memo); // save → UPDATE 실행
            System.out.println("업데이트 완료: " + memo);
        } else {
            System.out.println("해당 ID의 메모가 존재하지 않습니다.");
        }
    }

    /**
     * 3. 단일 조회 (READ)
     * - 특정 ID(여기선 1번)의 메모 한 개를 읽어옴
     * - 있으면 출력, 없으면 메시지 출력
     */
    @Test
    public void readTest() {
        Optional<Memo> result = memoRepository.findById(1L);

        result.ifPresentOrElse(
                memo -> System.out.println("조회 결과: " + memo),
                () -> System.out.println("해당 메모가 존재하지 않음"));
    }

    /**
     * 4. 전체 조회 (LIST)
     * - 저장된 모든 Memo를 가져옴
     * - forEach 로 하나씩 콘솔에 출력
     */
    @Test
    public void listTest() {
        System.out.println("전체 메모 목록:");
        memoRepository.findAll().forEach(System.out::println);
    }

    /**
     * 5. DELETE 테스트
     * - ID가 1인 메모가 존재할 경우 삭제
     * - 없으면 삭제 불가 메시지 출력
     */
    @Test
    public void dl() {
        Long idToDelete = 1L;

        if (memoRepository.existsById(idToDelete)) {
            memoRepository.deleteById(idToDelete); // DELETE FROM memo WHERE id = 1
            System.out.println("삭제 완료: ID " + idToDelete);
        } else {
            System.out.println("삭제할 메모가 존재하지 않음");
        }
    }
}
