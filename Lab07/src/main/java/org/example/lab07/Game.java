package org.example.lab07;

import java.util.*;

public class Game {
    private final List<Player> players = new ArrayList<>();
    private final Bag bag = new Bag();
    private final Board board = new Board();
    private final Dictionary dictionary = new Dictionary();

    public void addPlayer(String name) {
        players.add(new Player(name, bag, board, dictionary));
    }

    public void start() {
        List<Thread> threads = new ArrayList<>();
        for (Player player : players) {
            Thread thread = new Thread(player, player.getName());
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        Player winner = players.stream()
                .max(Comparator.comparingInt(Player::getScore))
                .orElse(null);
        System.out.println();
        System.out.println("Game Over! Winner: " + (winner != null ? winner.getName() + " with " + winner.getScore() + " points" : "No winner"));
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.addPlayer("Alice");
        game.addPlayer("Bob");
        game.addPlayer("Charlie");
        game.start();
    }
}