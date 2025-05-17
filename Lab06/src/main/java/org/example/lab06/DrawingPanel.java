package org.example.lab06;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;

public class DrawingPanel extends Pane{

    private final List<Circle> dots = new ArrayList<>();
    private final List<Line> lines = new ArrayList<>();
    private Circle previousDot = null;
    private Map<Circle, List<Circle>> blueConnections = new HashMap<>();
    private Map<Circle, List<Circle>> redConnections = new HashMap<>();
    private int numberOfDots = 10;
    private boolean isPlayerBlueTurn = true;
    private boolean isAI = false;

    private double blueScore = 0.0;
    private double redScore = 0.0;

    private transient Label blueScoreLabel;
    private transient Label redScoreLabel;

    public DrawingPanel(boolean isAI) {
        this.setPrefSize(800, 600);
        this.isAI = isAI;
    }

    public void setScoreLabels(Label blueScoreLabel, Label redScoreLabel) {
        this.blueScoreLabel = blueScoreLabel;
        this.redScoreLabel = redScoreLabel;
    }

    public int getNumberOfDots() {
        return numberOfDots;
    }

    public void resetGame(int numberOfDots, boolean isAI) {
        this.numberOfDots = numberOfDots;
        dots.clear();
        lines.clear();
        blueConnections.clear();
        redConnections.clear();
        blueScore = 0.0;
        redScore = 0.0;
        updateScores();
        previousDot = null;
        isPlayerBlueTurn = true;
        this.getChildren().clear();
        this.isAI = isAI;
        generateDots();
    }

    public void generateDots() {
        Random random = new Random();
        for (int i = 0; i < numberOfDots; i++) {
            int x = random.nextInt((int) this.getPrefWidth() - 40) + 20;
            int y = random.nextInt((int) this.getPrefHeight() - 40) + 20;
            Circle dot = new Circle(x, y, 10, Color.BLACK);
            dots.add(dot);
            this.getChildren().add(dot);

            dot.setOnMouseClicked(event -> MouseEvent.handleDotClick(this, dot));
        }
    }

    public void saveGameState() {
        try (FileOutputStream fileOut = new FileOutputStream("game_state.ser");
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(this);
            showAlert("Save Successful", "Game state has been saved to game_state.ser");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Save Failed", "An error occurred while saving the game state.");
        }
    }

    public void loadGameState() {
        try (FileInputStream fileIn = new FileInputStream("game_state.ser");
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            DrawingPanel loadedPanel = (DrawingPanel) objectIn.readObject();
            resetGame(loadedPanel.numberOfDots);
            this.dots.addAll(loadedPanel.dots);
            this.lines.addAll(loadedPanel.lines);
            this.blueConnections = loadedPanel.blueConnections;
            this.redConnections = loadedPanel.redConnections;
            this.blueScore = loadedPanel.blueScore;
            this.redScore = loadedPanel.redScore;

            updateScores();
            this.getChildren().clear();
            this.getChildren().addAll(dots);
            this.getChildren().addAll(lines);

            showAlert("Load Successful", "Game state has been loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            showAlert("Load Failed", "An error occurred while loading the game state.");
        }
    }

    public void exportGameBoard() {
        WritableImage image = this.snapshot(null, null);
        File file = new File("game_snapshot.png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            showAlert("Export Successful", "Game board has been exported as game_snapshot.png.");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Export Failed", "An error occurred while exporting the game board.");
        }
    }

    public void updateScores() {
        blueScoreLabel.setText(String.format("Blue Score: %.1f", blueScore));
        redScoreLabel.setText(String.format("Red Score: %.1f", redScore));
    }

    public void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public Map<Circle, List<Circle>> getBlueConnections() {
        return blueConnections;
    }

    public Map<Circle, List<Circle>> getRedConnections() {
        return redConnections;
    }

    public boolean isPlayerBlueTurn() {
        return isPlayerBlueTurn;
    }

    public void togglePlayerTurn() {
        isPlayerBlueTurn = !isPlayerBlueTurn;
    }

    public Circle getPreviousDot() {
        return previousDot;
    }

    public void setPreviousDot(Circle previousDot) {
        this.previousDot = previousDot;
    }

    public double getBlueScore() {
        return blueScore;
    }

    public double getRedScore() {
        return redScore;
    }

    public void addBlueScore(double score) {
        this.blueScore += score;
    }

    public void addRedScore(double score) {
        this.redScore += score;
    }
}