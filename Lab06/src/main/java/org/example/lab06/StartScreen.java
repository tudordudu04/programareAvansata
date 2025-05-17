package org.example.lab06;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartScreen {

    private ConfigPanel configPanel;
    private ControlPanel controlPanel;
    private DrawingPanel drawingPanel;
    private boolean isAI;

    public void showStartScreen(Stage primaryStage, boolean isAI) {
        primaryStage.setTitle("Dot Game");

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1100, 800);

        drawingPanel = new DrawingPanel(isAI);
        configPanel = new ConfigPanel(drawingPanel);
        controlPanel = new ControlPanel(drawingPanel, primaryStage);

        root.setTop(configPanel);
        root.setCenter(drawingPanel);
        root.setBottom(controlPanel);

        primaryStage.setScene(scene);
        primaryStage.show();

        drawingPanel.generateDots();

    }
}