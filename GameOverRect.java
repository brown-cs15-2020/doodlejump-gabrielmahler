package doodlejump;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class GameOverRect {
    Rectangle gameOver;
    Label _label;

    /**
     * Small class that defines a rectangle with label which display when the game is over
     */
    public GameOverRect(Pane gamePlayPane) { // takes in the pane, to which the rectangle should be added to
        // setting up the rectangle
        gameOver = new Rectangle();
        gameOver.setHeight(Constants.GAME_PANE_HEIGHT/2);
        gameOver.setWidth(Constants.GAME_PANE_WIDTH/2);
        gameOver.setY(Constants.GAME_PANE_HEIGHT/4);
        gameOver.setX(Constants.GAME_PANE_WIDTH/4);
        gameOver.setFill(Color.GRAY);
        gameOver.setStroke(Color.BLACK);

        // setting up the label - text
        _label = new Label("Game Over");
        _label.setTextFill(Color.WHITE);
        _label.setLayoutX(Constants.GAME_PANE_WIDTH/4 + 40);
        _label.setLayoutY(Constants.GAME_PANE_HEIGHT/4 + 80);
        _label.setFont(new Font(35));

        // adding both to the pane
        gamePlayPane.getChildren().addAll(gameOver, _label);

    }

}
