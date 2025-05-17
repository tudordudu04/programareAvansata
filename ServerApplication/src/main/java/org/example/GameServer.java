package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.exit;

public class GameServer {
    private int port;
    private boolean running = true;

    public GameServer(int port) {
        this.port = port;
    }

    public void start() {
        System.out.println("Game Server is starting on port " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (running) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);
                new ClientThread(clientSocket, this).start();
            }
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }

    public void stop() {
        running = false;
        System.out.println("Game Server is stopping...");
        exit(0);
    }

}