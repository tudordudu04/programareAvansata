package org.example.lab07;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Board {
    private final List<String> words = new ArrayList<>();
    private final Lock lock = new ReentrantLock();

    public void submitWord(String name, String word) {
        lock.lock();
        try {
            words.add(word);
            System.out.println("["+ name + "] Word added to board: " + word);
        } finally {
            lock.unlock();
        }
    }

    public List<String> getWords() {
        return words;
    }
}