package org.example.lab07;

import java.util.*;

public class Dictionary {
    private final Set<String> validWords = new HashSet<>();

    public Dictionary() {
//        validWords.add("CAT");
//        validWords.add("DOG");
//        validWords.add("BIRD");
//        validWords.add("HOUSE");
//        validWords.add("JAVA");
    }

    public boolean isValidWord(String word) {
//        return validWords.contains(word);
        return true;
    }
}