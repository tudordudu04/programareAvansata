package org.example.lab06;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainFrame extends Application {
    private StartScreen startingScreen;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dot Game");
        startingScreen = new StartScreen();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1100, 800);

        Label welcomeLabel = new Label("Welcome to Dot Game");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        root.setTop(welcomeLabel);
        BorderPane.setAlignment(welcomeLabel, Pos.CENTER);

        // Options Box
        VBox optionsBox = new VBox(20);
        optionsBox.setAlignment(Pos.CENTER);

        Label modeLabel = new Label("Select Game Mode:");
        modeLabel.setStyle("-fx-font-size: 18px;");

        Button twoPlayersButton = new Button("2 Players");
        Button playWithAIButton = new Button("Play with AI");

        optionsBox.getChildren().addAll(modeLabel, twoPlayersButton, playWithAIButton);
        root.setCenter(optionsBox);

        // Set scene and show stage
        primaryStage.setScene(scene);
        primaryStage.show();

        // Button Actions
        twoPlayersButton.setOnAction(event -> {
            startingScreen.showStartScreen(primaryStage, false);
        });

        playWithAIButton.setOnAction(event -> {
            startingScreen.showStartScreen(primaryStage, true);
        });

    }
}