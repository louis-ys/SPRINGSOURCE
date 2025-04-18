package com.example.jpa.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.dto.MemoDTO;
import com.example.jpa.entity.Memo;
import com.example.jpa.repository.MemoRepository;

@SpringBootTest
public class MemoserviceTest {

    @Autowired
    private Memoservice memoservice;

    @Autowired
    private MemoRepository memoRepository;

    @Test
    public void getList() {
        List<MemoDTO> list = memoservice.getList();
        list.forEach(dto -> System.out.println(dto));
    }

    @Test
    public void checkMemoExist() {
        memoRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void getRowTest() {
        MemoDTO dto = memoservice.getRow(2L);
        System.out.println(dto);
    }

    @Test
    public void memoUpdateTest() {
        // 1. 먼저 기존 메모 데이터를 저장 (없으면 업데이트할 수 없음)
        Memo memo = Memo.builder()
                .memoText("처음 저장한 메모입니다")
                .build();
        memoRepository.save(memo); // DB에 저장 → ID 자동 생성됨

        // 2. 저장된 ID로 다시 메모 가져오기
        Memo savedMemo = memoRepository.findById(memo.getMno())
                .orElseThrow(() -> new RuntimeException("저장된 메모를 찾을 수 없습니다."));

        // 3. 내용 수정
        savedMemo.setMemoText("수정된 메모 내용입니다!");

        // 4. 수정된 메모 다시 저장
        memoRepository.save(savedMemo); // save()는 ID가 같으면 UPDATE 수행

        // 5. 결과 확인
        Memo updatedMemo = memoRepository.findById(savedMemo.getMno())
                .orElseThrow();
        System.out.println("수정 후 메모 내용: " + updatedMemo.getMemoText());
    }

    @Test
    public void memoDeleteTest() {
        memoservice.memoDelete(5L);
    }

    @Test
    public void memoCreateTest() {
        MemoDTO dto = MemoDTO.builder().memoText("memo 추가").build();
        System.out.println("추가된 mno" + memoservice.memoCreate(dto));
    }
}
