package org.example.lab06;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;

public class HelloApplication extends Application {
    private final List<Dot> dots = new ArrayList<>();
    private final List<Line> lines = new ArrayList<>();
    private Dot previousDot = null;
    private Map<Dot, List<Dot>> blueConnections = new HashMap<>();
    private Map<Dot, List<Dot>> redConnections = new HashMap<>();
    private Map<Dot, Dot> blueParent = new HashMap<>();
    private Map<Dot, Dot> redParent = new HashMap<>();
    private int numberOfDots = 10;
    private boolean isPlayerBlueTurn = true;

    // New: Scores for players
    private double blueScore = 0.0;
    private double redScore = 0.0;

    // New: Labels to display scores
    private Label blueScoreLabel = new Label("Blue Score: 0.0");
    private Label redScoreLabel = new Label("Red Score: 0.0");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dot Game");

        BorderPane root = new BorderPane();

        // Configuration Panel
        HBox configPanel = createConfigurationPanel(primaryStage);
        root.setTop(configPanel);

        // Canvas
        Canvas canvas = new Canvas(800, 600);
        drawCanvas(canvas);
        root.setCenter(canvas);

        // Control Panel
        HBox controlPanel = createControlPanel(primaryStage, canvas);
        root.setBottom(controlPanel);

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            handleCanvasClick(event, canvas);
        });

        Scene scene = new Scene(root, 800, 700);
        primaryStage.setScene(scene);
        primaryStage.show();

        generateDots(canvas);
    }

    private HBox createConfigurationPanel(Stage stage) {
        Label label = new Label("Number of Dots:");
        TextField textField = new TextField(String.valueOf(numberOfDots));
        Button newGameButton = new Button("New Game");

        newGameButton.setOnAction(e -> {
            try {
                numberOfDots = Integer.parseInt(textField.getText());
                if (numberOfDots < 4) throw new NumberFormatException();
                dots.clear();
                lines.clear();
                blueConnections.clear();
                redConnections.clear();
                blueParent.clear();
                redParent.clear();
                blueScore = 0.0;
                redScore = 0.0;
                updateScores();
                generateDots(((BorderPane) stage.getScene().getRoot()).getCenter());
            } catch (NumberFormatException ex) {
                showAlert("Invalid Input", "Please enter a valid number.");
            }
        });

        // New: Add score labels to the configuration panel
        HBox configPanel = new HBox(10, blueScoreLabel, label, textField, newGameButton, redScoreLabel);
        configPanel.setPadding(new Insets(10));
        configPanel.setAlignment(Pos.CENTER);
        return configPanel;
    }

    private HBox createControlPanel(Stage stage, Canvas canvas) {
        Button loadButton = new Button("Load");
        Button saveButton = new Button("Save");
        Button exitButton = new Button("Exit");

        exitButton.setOnAction(e -> stage.close());

        HBox controlPanel = new HBox(10, loadButton, saveButton, exitButton);
        controlPanel.setPadding(new Insets(10));
        controlPanel.setAlignment(Pos.CENTER);
        return controlPanel;
    }

    private void generateDots(Object canvasObj) {
        if (canvasObj instanceof Canvas) {
            Canvas canvas = (Canvas) canvasObj;
            dots.clear();
            Random random = new Random();
            for (int i = 0; i < numberOfDots; i++) {
                int x = random.nextInt((int) canvas.getWidth() - 40) + 20;
                int y = random.nextInt((int) canvas.getHeight() - 40) + 20;
                dots.add(new Dot(x, y));
            }
            drawCanvas(canvas);
        }
    }

    private void handleCanvasClick(MouseEvent event, Canvas canvas) {
        for (Dot dot : dots) {
            if (dot.isClicked(event.getX(), event.getY())) {
                if (previousDot == null) {
                    // First dot is clicked
                    if (isPlayerBlueTurn) {
                        if (blueConnections.containsKey(dot) || !redConnections.containsKey(dot)) {
                            previousDot = dot;
                        } else {
                            showAlert("Invalid Move", "You can only start from your own paths or unconnected dots!");
                        }
                    } else {
                        if (redConnections.containsKey(dot) || !blueConnections.containsKey(dot)) {
                            previousDot = dot;
                        } else {
                            showAlert("Invalid Move", "You can only start from your own paths or unconnected dots!");
                        }
                    }
                } else {
                    // Second dot is clicked
                    if (isPlayerBlueTurn) {
                        if (isValidMove(blueConnections, redConnections, previousDot, dot)) {
                            addConnection(blueConnections, previousDot, dot);
                            double lineLength = calculateLineLength(previousDot, dot);
                            blueScore += lineLength; // Update red player's score
                            lines.add(new Line(previousDot, dot, Color.BLUE));
                            updateScores();
                        } else {
                            showAlert("Invalid Move", "You can only connect to your own paths or start a new path!");
                            previousDot = null; // Reset previousDot after an invalid move
                            return; // Exit early to prevent further processing
                        }
                    } else {
                        if (isValidMove(redConnections, blueConnections, previousDot, dot)) {
                            double lineLength = calculateLineLength(previousDot, dot);
                            redScore += lineLength; // Update red player's score
                            lines.add(new Line(previousDot, dot, Color.RED));
                            updateScores();
                        } else {
                            showAlert("Invalid Move", "You can only connect to your own paths or start a new path!");
                            previousDot = null; // Reset previousDot after an invalid move
                            return; // Exit early to prevent further processing
                        }
                    }

                    // Switch player turn and reset previousDot
                    isPlayerBlueTurn = !isPlayerBlueTurn;
                    previousDot = null;

                    // Redraw the canvas
                    drawCanvas(canvas);
                    return;
                }
            }
        }
    }

    private void updateScores() {
        blueScoreLabel.setText(String.format("Blue Score: %.1f", blueScore));
        redScoreLabel.setText(String.format("Red Score: %.1f", redScore));
    }

    private boolean isValidMove(Map<Dot, List<Dot>> playerConnections, Map<Dot, List<Dot>> opponentConnections, Dot start, Dot end) {
        if(!playerConnections.isEmpty())
            if(!playerConnections.containsKey(start) && !playerConnections.containsKey(end)) {
                return false;
            }
        return !opponentConnections.containsKey(start) && !opponentConnections.containsKey(end);
    }

    private double calculateLineLength(Dot start, Dot end) {
        return Math.sqrt(Math.pow(end.getX() - start.getX(), 2) + Math.pow(end.getY() - start.getY(), 2));
    }

    private void addConnection(Map<Dot, List<Dot>> connections, Dot start, Dot end) {
        connections.putIfAbsent(start, new ArrayList<>());
        connections.putIfAbsent(end, new ArrayList<>());
        connections.get(start).add(end);
        connections.get(end).add(start);
    }

    private void drawCanvas(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Draw dots
        for (Dot dot : dots) {
            gc.setFill(Color.BLACK);
            gc.fillOval(dot.getX() - 5, dot.getY() - 5, 10, 10);
        }

        // Draw lines
        for (Line line : lines) {
            gc.setStroke(line.getColor());
            gc.setLineWidth(2);
            gc.strokeLine(line.getStart().getX(), line.getStart().getY(),
                    line.getEnd().getX(), line.getEnd().getY());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    static class Dot {
        private final int x;
        private final int y;

        public Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public boolean isClicked(double mouseX, double mouseY) {
            return Math.sqrt(Math.pow(mouseX - x, 2) + Math.pow(mouseY - y, 2)) <= 10;
        }
    }

    static class Line {
        private final Dot start;
        private final Dot end;
        private final Color color;

        public Line(Dot start, Dot end, Color color) {
            this.start = start;
            this.end = end;
            this.color = color;
        }

        public Dot getStart() {
            return start;
        }

        public Dot getEnd() {
            return end;
        }

        public Color getColor() {
            return color;
        }
    }
}