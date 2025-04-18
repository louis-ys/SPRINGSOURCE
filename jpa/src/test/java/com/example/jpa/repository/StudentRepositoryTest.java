package com.example.jpa.repository;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.Student;
import com.example.jpa.entity.Student.Grade;
import com.example.jpa.repository.StudentRepository;

@SpringBootTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void insertTest() {

        LongStream.range(1, 11).forEach(i -> {
            Student student = Student.builder()
                    .name("홍길동")
                    .grade(Grade.JUNIOR)
                    .gender("M")
                    .build();

            studentRepository.save(student);
        });
    }

    @Test // 테스트 메소드는 무조건 void여야 함
    public void updateTest() {
        Student student = studentRepository.findById(2L).get();
        student.setGrade(Grade.SENIOR);
        studentRepository.save(student);
    }

    // public void selectOneTest(){

    // }

}
