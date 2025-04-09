package org.example.lab07;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bag {
    private final List<Tile> tiles = new ArrayList<>();
    private final Lock lock = new ReentrantLock();

    public Bag() {
        Random random = new Random();
        for (char c = 'A'; c <= 'Z'; c++) {
            for (int i = 0; i < 5; i++) {
                tiles.add(new Tile(c, random.nextInt(10) + 1));
            }
        }
        Collections.shuffle(tiles);
    }

    public List<Tile> extractTiles(int count) {
        lock.lock();
        try {
            List<Tile> extracted = new ArrayList<>();
            for (int i = 0; i < count && !tiles.isEmpty(); i++) {
                extracted.add(tiles.remove(tiles.size() - 1));
            }
            return extracted;
        } finally {
            lock.unlock();
        }
    }

    public boolean isEmpty() {
        return tiles.isEmpty();
    }
}