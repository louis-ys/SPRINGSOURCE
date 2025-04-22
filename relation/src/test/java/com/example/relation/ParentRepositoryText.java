package com.example.relation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.relation.entity.cascade.Child;
import com.example.relation.entity.cascade.Parent;
import com.example.relation.repository.cascade.ChildRepository;
import com.example.relation.repository.cascade.ParentRepository;

import jakarta.persistence.Column;
import jakarta.transaction.Transactional;

@SpringBootTest
public class ParentRepositoryText {
    @Autowired
    private ParentRepository parentRepository;
    @Autowired
    private ChildRepository childRepository;

    @Test
    public void testInsert() {
        Parent parent = new Parent();
        parent.setName("parent1");
        parentRepository.save(parent);

        Child child = new Child();
        child.setName("child1");
        child.setParent(parent);
        childRepository.save(child);

        child = new Child();
        child.setName("child2");
        child.setParent(parent);
        childRepository.save(child);

    }

    @Test
    public void testInsert2() {
        Parent parent = new Parent();
        parent.setName("parent3");

        parent.getChilds().add(Child.builder().name("홍길동").parent(parent).build());
        parent.getChilds().add(Child.builder().name("성춘향").parent(parent).build());
        parent.getChilds().add(Child.builder().name("박보검").parent(parent).build());

        parentRepository.save(parent);

    }

    @Test
    public void deleteTest() {
        parentRepository.deleteById(4l);

    }

    @Column
    @Transactional
    @Test
    public void deleteTest2() {
        Parent parent = parentRepository.findById(7L).get();
        // 자식 조회
        parent.getChilds().remove(0);
        System.out.println(parent.getChilds());
        parentRepository.save(parent);

    }

}
