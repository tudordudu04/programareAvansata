package org.example.lab06;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ConfigPanel extends HBox {
    private Label blueScoreLabel;
    private Label redScoreLabel;
    private TextField numberOfDotsField;

    public ConfigPanel(DrawingPanel drawingPanel) {
        blueScoreLabel = new Label("Blue Score: 0.0");
        redScoreLabel = new Label("Red Score: 0.0");
        Label label = new Label("Number of Dots:");
        numberOfDotsField = new TextField(String.valueOf(drawingPanel.getNumberOfDots()));
        CheckBox playWithAIBox = new CheckBox("Play with AI");
        Button newGameButton = new Button("New Game");

        newGameButton.setOnAction(e -> {
            try {
                int numberOfDots = Integer.parseInt(numberOfDotsField.getText());
                if (numberOfDots < 4) throw new NumberFormatException();
                boolean isAI = playWithAIBox.isSelected(); // Player 2 is AI only if checkbox is checked
                drawingPanel.resetGame(numberOfDots, isAI);
            } catch (NumberFormatException ex) {
                drawingPanel.showAlert("Invalid Input", "Please enter a valid number.");
            }
        });

        this.getChildren().addAll(blueScoreLabel, label, numberOfDotsField, newGameButton, redScoreLabel);
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);

        drawingPanel.setScoreLabels(blueScoreLabel, redScoreLabel);
    }
}