package com.example.board.entity;

import java.util.concurrent.ThreadLocalRandom;

public class RandomNameGenerator {

    private static final String[] NAMES = {
            "홍길동", "김철수", "이영희", "박민수", "최수정",
            "장동건", "유아인", "송중기", "박보검", "박소담"
    };

    public static String getRandomName() {
        int idx = ThreadLocalRandom.current().nextInt(NAMES.length);
        return NAMES[idx];

    }
}
