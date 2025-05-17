package org.example.lab06;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MouseEvent {

    public static void handleDotClick(DrawingPanel drawingPanel, Circle dot) {
        Circle previousDot = drawingPanel.getPreviousDot();
        if (previousDot == null) {
            if (drawingPanel.isPlayerBlueTurn() && !drawingPanel.getRedConnections().containsKey(dot)) {
                drawingPanel.setPreviousDot(dot);
                dot.setFill(Color.BLUE);
            } else if (!drawingPanel.isPlayerBlueTurn() && !drawingPanel.getBlueConnections().containsKey(dot)) {
                drawingPanel.setPreviousDot(dot);
                dot.setFill(Color.RED);
            } else
                drawingPanel.showAlert("Invalid Move", "You can only start from your own paths or unconnected dots!");
        } else {
            if (drawingPanel.isPlayerBlueTurn()) {
                if (isValidMove(drawingPanel.getBlueConnections(), drawingPanel.getRedConnections(), previousDot, dot)) {
                    addConnection(drawingPanel, drawingPanel.getBlueConnections(), previousDot, dot, Color.BLUE);
                    double lineLength = calculateLineLength(previousDot, dot);
                    drawingPanel.addBlueScore(lineLength);
                    drawingPanel.updateScores();
                } else {
                    drawingPanel.showAlert("Invalid Move", "You can only connect to your own paths or start a new path!");
                    previousDot.setFill(Color.BLACK);
                    drawingPanel.setPreviousDot(null);
                    return;
                }
            } else {
                if (isValidMove(drawingPanel.getRedConnections(), drawingPanel.getBlueConnections(), previousDot, dot)) {
                    addConnection(drawingPanel, drawingPanel.getRedConnections(), previousDot, dot, Color.RED);
                    double lineLength = calculateLineLength(previousDot, dot);
                    drawingPanel.addRedScore(lineLength);
                    drawingPanel.updateScores();
                } else {
                    drawingPanel.showAlert("Invalid Move", "You can only connect to your own paths or start a new path!");
                    previousDot.setFill(Color.BLACK);
                    drawingPanel.setPreviousDot(null);
                    return;
                }
            }

            drawingPanel.togglePlayerTurn();
            previousDot.setFill(Color.BLACK);
            drawingPanel.setPreviousDot(null);
        }
    }

    private static boolean isValidMove(
            Map<Circle, List<Circle>> playerConnections,
            Map<Circle, List<Circle>> opponentConnections,
            Circle start,
            Circle end) {
        if (!playerConnections.isEmpty())
            if (!playerConnections.containsKey(start) && !playerConnections.containsKey(end)) {
                return false;
            }
        return !opponentConnections.containsKey(start) && !opponentConnections.containsKey(end);
    }

    private static void addConnection(
            DrawingPanel drawingPanel,
            Map<Circle, List<Circle>> connections,
            Circle start,
            Circle end,
            Color color) {
        connections.putIfAbsent(start, new ArrayList<>());
        connections.putIfAbsent(end, new ArrayList<>());
        connections.get(start).add(end);
        connections.get(end).add(start);

        Line line = new Line(start.getCenterX(), start.getCenterY(), end.getCenterX(), end.getCenterY());
        line.setStroke(color);
        line.setStrokeWidth(2);
        drawingPanel.getChildren().add(line);
    }

    private static double calculateLineLength(Circle start, Circle end) {
        return Math.sqrt(Math.pow(end.getCenterX() - start.getCenterX(), 2) +
                Math.pow(end.getCenterY() - start.getCenterY(), 2));
    }
}