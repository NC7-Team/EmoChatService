package com.example.emotion_chat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNameGenerator {
    private static List<String> names = new ArrayList<>();

    public static String generateRandomName() {
        String[] familyNames = {"김", "이", "박", "정", "최", "강", "한", "오", "윤"};
        String[] givenNames = {"춘자", "민준", "서연", "지우", "영호", "영희", "성민", "지은", "민서"};

        Random random = new Random();
        String randomFamilyName = familyNames[random.nextInt(familyNames.length)];
        String randomGivenName = givenNames[random.nextInt(givenNames.length)];

        return randomFamilyName + randomGivenName;
    }

    public static String randomName(int userId) {
        if (names.isEmpty()) {
            for (int i = 0; i < 1000; i++) {
                String name = generateRandomName();
                names.add(name);
            }
        }
        return names.get(userId);
    }
}

