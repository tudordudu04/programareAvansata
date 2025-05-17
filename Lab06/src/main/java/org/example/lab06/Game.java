package org.example.lab06;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;

public class Game extends Application implements Serializable {
    private static final long serialVersionUID = 1L;

    private final List<Circle> dots = new ArrayList<>();
    private final List<Line> lines = new ArrayList<>();
    private Circle previousDot = null;
    private Map<Circle, List<Circle>> blueConnections = new HashMap<>();
    private Map<Circle, List<Circle>> redConnections = new HashMap<>();
    private int numberOfDots = 10;
    private boolean isPlayerBlueTurn = true;

    private double blueScore = 0.0;
    private double redScore = 0.0;

    private Label blueScoreLabel = new Label("Blue Score: 0.0");
    private Label redScoreLabel = new Label("Red Score: 0.0");

    private transient Pane boardPane;

    // New fields for AI
    private boolean isPlayerBlueAI = false;
    private boolean isPlayerRedAI = false;
    private int blueAIDifficulty = 1; // 1 = Easy, 3 = Hard
    private int redAIDifficulty = 1;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dot Game");

        // Create the initial configuration screen
        BorderPane configScreen = new BorderPane();
        Scene configScene = new Scene(configScreen, 800, 700);

        Label welcomeLabel = new Label("Welcome to Dot Game");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        configScreen.setTop(welcomeLabel);
        BorderPane.setAlignment(welcomeLabel, Pos.CENTER);

        VBox optionsBox = new VBox(20);
        optionsBox.setAlignment(Pos.CENTER);
        Label modeLabel = new Label("Select Game Mode:");
        modeLabel.setStyle("-fx-font-size: 18px;");
        Button twoPlayersButton = new Button("2 Players");
        Button playWithAIButton = new Button("Play with AI");

        optionsBox.getChildren().addAll(modeLabel, twoPlayersButton, playWithAIButton);
        configScreen.setCenter(optionsBox);

        primaryStage.setScene(configScene);
        primaryStage.show();

        // Event handlers for buttons
        twoPlayersButton.setOnAction(event -> {
            isPlayerBlueAI = false;
            isPlayerRedAI = false;
            showGameBoard(primaryStage);
        });

        playWithAIButton.setOnAction(event -> {
            isPlayerBlueAI = false; // For the purpose of this example, Player 1 is human
            isPlayerRedAI = true;  // Player 2 is AI
            redAIDifficulty = 2;   // Default AI difficulty
            showGameBoard(primaryStage);
        });
    }
    private void showGameBoard(Stage primaryStage) {
        BorderPane root = new BorderPane();

        HBox configPanel = createConfigurationPanel(primaryStage);
        root.setTop(configPanel);

        boardPane = new Pane();
        boardPane.setPrefSize(800, 600);
        root.setCenter(boardPane);

        HBox controlPanel = createControlPanel(primaryStage);
        root.setBottom(controlPanel);

        Scene scene = new Scene(root, 800, 700);
        primaryStage.setScene(scene);
        primaryStage.show();

        generateDots();
    }
    private HBox createConfigurationPanel(Stage stage) {
        Label label = new Label("Number of Dots:");
        TextField textField = new TextField(String.valueOf(numberOfDots));
        Button newGameButton = new Button("New Game");
        CheckBox playWithAIBox = new CheckBox("Play with AI");

        newGameButton.setOnAction(e -> {
            try {
                numberOfDots = Integer.parseInt(textField.getText());
                if (numberOfDots < 4) throw new NumberFormatException();
                dots.clear();
                lines.clear();
                blueConnections.clear();
                redConnections.clear();
                blueScore = 0.0;
                redScore = 0.0;
                updateScores();
                previousDot = null;
                isPlayerBlueTurn = true;
                boardPane.getChildren().clear(); // Clear the board
                generateDots();

                // Set AI mode based on the checkbox
                isPlayerBlueAI = false; // Player 1 is always human
                isPlayerRedAI = playWithAIBox.isSelected(); // Player 2 is AI only if checkbox is checked
            } catch (NumberFormatException ex) {
                showAlert("Invalid Input", "Please enter a valid number.");
            }
        });

        HBox configPanel = new HBox(10, blueScoreLabel, label, textField, newGameButton, playWithAIBox, redScoreLabel);
        configPanel.setPadding(new Insets(10));
        configPanel.setAlignment(Pos.CENTER);
        return configPanel;
    }
    private HBox createControlPanel(Stage stage) {
        Button loadButton = new Button("Load");
        Button saveButton = new Button("Save");
        Button exitButton = new Button("Exit");
        Button exportButton = new Button("Export");

        saveButton.setOnAction(e -> saveGameState());
        loadButton.setOnAction(e -> loadGameState(stage));
        exportButton.setOnAction(e -> exportGameBoard());
        exitButton.setOnAction(e -> stage.close());

        HBox controlPanel = new HBox(10, loadButton, saveButton, exitButton, exportButton);
        controlPanel.setPadding(new Insets(10));
        controlPanel.setAlignment(Pos.CENTER);
        return controlPanel;
    }

    private void saveGameState() {
        try (FileOutputStream fileOut = new FileOutputStream("game_state.ser");
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(this);
            showAlert("Save Successful", "Game state has been saved to game_state.ser");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Save Failed", "An error occurred while saving the game state.");
        }
    }//Nu merge

    private void loadGameState(Stage stage) {
        try (FileInputStream fileIn = new FileInputStream("game_state.ser");
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            Game loadedApp = (Game) objectIn.readObject();

            this.dots.clear();
            this.dots.addAll(loadedApp.dots);
            this.lines.clear();
            this.lines.addAll(loadedApp.lines);
            this.blueConnections = loadedApp.blueConnections;
            this.redConnections = loadedApp.redConnections;
            this.numberOfDots = loadedApp.numberOfDots;
            this.isPlayerBlueTurn = loadedApp.isPlayerBlueTurn;
            this.blueScore = loadedApp.blueScore;
            this.redScore = loadedApp.redScore;

            updateScores();
            boardPane.getChildren().clear();
            boardPane.getChildren().addAll(dots);
            boardPane.getChildren().addAll(lines);

            showAlert("Load Successful", "Game state has been loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            showAlert("Load Failed", "An error occurred while loading the game state.");
        }
    } //Nu merge

    private void exportGameBoard() {
        WritableImage image = boardPane.snapshot(null, null);
        File file = new File("game_snapshot.png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            showAlert("Export Successful", "Game board has been exported as game_snapshot.png.");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Export Failed", "An error occurred while exporting the game board.");
        }
    }

    private void generateDots() {
        Random random = new Random();
        for (int i = 0; i < numberOfDots; i++) {
            int x = random.nextInt((int) boardPane.getPrefWidth() - 40) + 20;
            int y = random.nextInt((int) boardPane.getPrefHeight() - 40) + 20;
            Circle dot = new Circle(x, y, 10, Color.BLACK);
            dots.add(dot);
            boardPane.getChildren().add(dot);

            dot.setOnMouseClicked(event -> handleDotClick(dot));
        }
    }

    private void handleDotClick(Circle dot) {
        if (isPlayerBlueTurn && isPlayerBlueAI) {
            performAIMove(blueConnections, redConnections, blueAIDifficulty, Color.BLUE);
        } else if (!isPlayerBlueTurn && isPlayerRedAI) {
            performAIMove(redConnections, blueConnections, redAIDifficulty, Color.RED);
        } else {
            // Existing human player logic
            if (previousDot == null) {
                if (isPlayerBlueTurn && !redConnections.containsKey(dot)) {
                    previousDot = dot;
                    previousDot.setFill(Color.BLUE);
                } else if (!isPlayerBlueTurn && !blueConnections.containsKey(dot)) {
                    previousDot = dot;
                    previousDot.setFill(Color.RED);
                } else
                    showAlert("Invalid Move", "You can only start from your own paths or unconnected dots!");
            } else {
                if (isPlayerBlueTurn) {
                    if (isValidMove(blueConnections, redConnections, previousDot, dot)) {
                        addConnection(blueConnections, previousDot, dot, Color.BLUE);
                        double lineLength = calculateLineLength(previousDot, dot);
                        blueScore += lineLength;
                        updateScores();
                    } else {
                        showAlert("Invalid Move", "You can only connect to your own paths or start a new path!");
                        previousDot.setFill(Color.BLACK);
                        previousDot = null;
                        return;
                    }
                } else {
                    if (isValidMove(redConnections, blueConnections, previousDot, dot)) {
                        addConnection(redConnections, previousDot, dot, Color.RED);
                        double lineLength = calculateLineLength(previousDot, dot);
                        redScore += lineLength;
                        previousDot.setFill(Color.BLACK);
                        updateScores();
                    } else {
                        showAlert("Invalid Move", "You can only connect to your own paths or start a new path!");
                        previousDot.setFill(Color.BLACK);
                        previousDot = null;
                        return;
                    }
                }

                if (blueConnections.size() + redConnections.size() == numberOfDots) {
                    if (blueScore < redScore)
                        showAlert("Game Over", "Blue wins!");
                    else if (redScore < blueScore)
                        showAlert("Game Over", "Red wins!");
                    else showAlert("Game Over", "Draw!");
                    return;
                }

                isPlayerBlueTurn = !isPlayerBlueTurn;
                previousDot.setFill(Color.BLACK);
                previousDot = null;
            }
        }
    }
    private void performAIMove(Map<Circle, List<Circle>> aiConnections, Map<Circle, List<Circle>> opponentConnections, int difficulty, Color color) {
        // Generate spanning trees
        List<SpanningTree> trees = generateSpanningTrees(aiConnections);

        // Handle case where no spanning trees are generated
        if (trees.isEmpty()) {
            showAlert("Game Over", "No valid moves left for the AI.");
            return;
        }

        SpanningTree chosenTree;

        // Select tree based on difficulty
        if (difficulty == 3) { // Hard
            chosenTree = trees.get(0);
        } else if (difficulty == 1) { // Easy
            chosenTree = trees.get(trees.size() - 1);
        } else { // Medium
            chosenTree = trees.get(trees.size() / 2);
        }

        // Select a single connection from the chosen tree
        if (!chosenTree.getConnections().isEmpty()) {
            Connection chosenConnection = chosenTree.getConnections().get(0); // Choose the first connection
            addConnection(aiConnections, chosenConnection.start, chosenConnection.end, color);
        }

        updateScores();
        isPlayerBlueTurn = !isPlayerBlueTurn; // Switch turn to human player
    }
    private List<SpanningTree> generateSpanningTrees(Map<Circle, List<Circle>> connections) {
        // Example implementation of a simple spanning tree generator
        List<SpanningTree> trees = new ArrayList<>();
        Set<Circle> visited = new HashSet<>();

        for (Circle dot : dots) {
            if (!visited.contains(dot)) {
                SpanningTree tree = new SpanningTree();
                dfs(dot, visited, tree, connections);
                trees.add(tree);
            }
        }

        // Sort trees by their "cost" (e.g., total connection lengths)
        trees.sort(Comparator.comparingDouble(SpanningTree::getCost));
        return trees;
    }
    private void dfs(Circle current, Set<Circle> visited, SpanningTree tree, Map<Circle, List<Circle>> connections) {
        visited.add(current);

        for (Circle neighbor : dots) {
            if (!visited.contains(neighbor) && isValidMove(connections, new HashMap<>(), current, neighbor)) {
                tree.getConnections().add(new Connection(current, neighbor));
                dfs(neighbor, visited, tree, connections);
            }
        }
    }
    private static class SpanningTree {
        private final List<Connection> connections = new ArrayList<>();

        public List<Connection> getConnections() {
            return connections;
        }

        public double getCost() {
            // Calculate the cost of the tree (e.g., total length of all connections)
            return connections.stream()
                    .mapToDouble(conn -> Math.sqrt(Math.pow(conn.start.getCenterX() - conn.end.getCenterX(), 2)
                            + Math.pow(conn.start.getCenterY() - conn.end.getCenterY(), 2)))
                    .sum();
        }
    }
    private static class Connection {
        private final Circle start;
        private final Circle end;

        public Connection(Circle start, Circle end) {
            this.start = start;
            this.end = end;
        }
    }
    private boolean isValidMove(Map<Circle, List<Circle>> playerConnections, Map<Circle, List<Circle>> opponentConnections, Circle start, Circle end) {
        if(!playerConnections.isEmpty())
            if(!playerConnections.containsKey(start) && !playerConnections.containsKey(end)) {
                return false;
            }
        return !opponentConnections.containsKey(start) && !opponentConnections.containsKey(end);
    }

    private void addConnection(Map<Circle, List<Circle>> connections, Circle start, Circle end, Color color) {
        connections.putIfAbsent(start, new ArrayList<>());
        connections.putIfAbsent(end, new ArrayList<>());
        connections.get(start).add(end);
        connections.get(end).add(start);

        Line line = new Line(start.getCenterX(), start.getCenterY(), end.getCenterX(), end.getCenterY());
        line.setStroke(color);
        line.setStrokeWidth(2);
        lines.add(line);
        boardPane.getChildren().add(line);
    }

    private double calculateLineLength(Circle start, Circle end) {
        return Math.sqrt(Math.pow(end.getCenterX() - start.getCenterX(), 2) + Math.pow(end.getCenterY() - start.getCenterY(), 2));
    }

    private void updateScores() {
        blueScoreLabel.setText(String.format("Blue Score: %.1f", blueScore));
        redScoreLabel.setText(String.format("Red Score: %.1f", redScore));
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}