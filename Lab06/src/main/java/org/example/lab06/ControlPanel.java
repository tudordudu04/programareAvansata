package org.example.lab06;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ControlPanel extends HBox {

    public ControlPanel(DrawingPanel drawingPanel, Stage stage) {
        Button loadButton = new Button("Load");
        Button saveButton = new Button("Save");
        Button exitButton = new Button("Exit");
        Button exportButton = new Button("Export");

        saveButton.setOnAction(e -> drawingPanel.saveGameState());
        loadButton.setOnAction(e -> drawingPanel.loadGameState());
        exportButton.setOnAction(e -> drawingPanel.exportGameBoard());
        exitButton.setOnAction(e -> stage.close());

        this.getChildren().addAll(loadButton, saveButton, exitButton, exportButton);
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
    }
}