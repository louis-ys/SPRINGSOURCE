package com.example.jpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.jpa.dto.MemoDTO;
import com.example.jpa.entity.Memo;
import com.example.jpa.repository.MemberRepository;
import com.example.jpa.repository.MemoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
public class Memoservice {

    private final MemberRepository memberRepository;
    // Repository 메소드 호출한 후 결과 받기

    private final MemoRepository memoRepository;

    // Memoservice(MemberRepository memberRepository) {
    // this.memberRepository = memberRepository;
    // }

    public List<MemoDTO> getList() {

        List<Memo> list = memoRepository.findAll();

        // MEmo => Memo DTO 옮기기
        // List<MemoDTO> memos = new ArrayList<>();
        // for (Memo memo : list) {
        // MemoDTO dto = MemoDTO.builder()
        // .mno(memo.getMno())
        // .memoText(memo.getMemoText())
        // .build();
        // memos.add(dto);
        // }

        // list.stream().forEach(memo -> System.out.println(memo));
        List<MemoDTO> memos = list.stream().map(memo -> entityToDto(memo))
                .collect(Collectors.toList());

        return memos;
    }

    public Long memoUpdate(MemoDTO dto) {
        Memo memo = memoRepository.findById(dto.getMno()).orElseThrow(EntityNotFoundException::new);
        memo.changeMemoText(dto.getMemoText());
        memo = memoRepository.save(memo);
        return memo.getMno();
    }

    public MemoDTO getRow(Long mno) {
        // MemoRepository를 이용해서 mno에 해당하는 Memo 엔티티를 가져온다.
        Memo memo = memoRepository.findById(mno)
                .orElseThrow(() -> new EntityNotFoundException("해당 메모를 찾을 수 없습니다."));

        // 가져온 Memo 엔티티를 DTO로 변환한다.
        MemoDTO dto = entityToDto(memo);

        // 변환된 DTO를 반환한다.
        return dto;
    }

    private MemoDTO entityToDto(Memo memo) {

        MemoDTO dto = MemoDTO.builder()
                .mno(memo.getMno())
                .memoText(memo.getMemoText())
                .creatDate(memo.getCrDateTime())
                .updateDate(memo.getUpDateTime())
                .build();
        return dto;
    }

    public void updateMemo(Long mno, String newText) {
        // 1. memoRepository를 이용해서 ID(mno)에 해당하는 Memo를 데이터베이스에서 찾아옴
        // 만약 해당 ID를 가진 Memo가 없으면 예외(EntityNotFoundException)를 발생시켜서 종료
        Memo memo = memoRepository.findById(mno)
                .orElseThrow(() -> new EntityNotFoundException("메모를 찾을 수 없습니다."));

        // 2. 찾아온 Memo 객체의 메모 내용을 새로운 텍스트로 변경함
        memo.setMemoText(newText);

        // 3. 수정된 내용을 데이터베이스에 저장함 (이미 있는 ID니까 UPDATE 실행됨)
        memoRepository.save(memo);
    }

    public void memoDelete(Long mno) {
        memoRepository.deleteById(mno);
    }

    // 메모를 생성하는 메서드. 클라이언트로부터 받은 MemoDTO를 DB에 저장하고, 저장된 메모의 ID를 반환해요.
    public Long memoCreate(MemoDTO dto) {

        // 1. MemoDTO → Memo 엔티티로 변환하는 메서드를 호출 (실제 DB에 저장할 수 있는 형태로 바꿔줘요)
        Memo memo = dtotoEntity(dto);

        // 2. 변환된 Memo 엔티티를 DB에 저장 (저장하면 insert SQL이 실행됨, ID 자동 생성됨)
        memo = memoRepository.save(memo);

        // 3. 저장된 엔티티에서 생성된 ID(mno)를 꺼내서 반환
        return memo.getMno();
    }

    // MemoDTO → Memo 엔티티로 변환하는 메서드
    private Memo dtotoEntity(MemoDTO memoDTO) {

        // MemoDTO에 있는 값을 꺼내서, Memo 엔티티를 생성
        Memo memo = Memo.builder()
                .mno(memoDTO.getMno()) // ID값 (보통은 null로 오고, 자동 생성됨)
                .memoText(memoDTO.getMemoText()) // 메모 내용 복사
                .build(); // Memo 객체 생성

        // 생성된 Memo 객체를 반환
        return memo;
    }
}
