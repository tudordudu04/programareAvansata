package org.example.lab07;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import com.github.javafaker.Faker;

public class Player implements Runnable {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1);
    private final int id;
    private final String name;
    private final Bag bag;
    private final Board board;
    private final Dictionary dictionary;
    private final List<Tile> hand = new ArrayList<>();
    private int score = 0;

    public Player(String name, Bag bag, Board board, Dictionary dictionary) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.name = name;
        this.bag = bag;
        this.board = board;
        this.dictionary = dictionary;
    }

    @Override
    public void run() {
        while (!bag.isEmpty()) {
            synchronized (hand) {
                while (hand.size() < 7 && !bag.isEmpty()) {
                    hand.addAll(bag.extractTiles(7 - hand.size()));
                }
            }

            StringBuilder word = new StringBuilder();
            synchronized (hand) {
                for (Tile tile : hand) {
                    word.append(tile.getLetter());
                }
                hand.clear();
            }

            String wordStr = word.toString();
            if (dictionary.isValidWord(wordStr)) {
                board.submitWord(this.name, wordStr);
                score += wordStr.length();
            }

            // Simulate delay
            try {
                Thread.sleep(new Random().nextInt(1000) + 500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(name + " finished with a score of " + score);
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}