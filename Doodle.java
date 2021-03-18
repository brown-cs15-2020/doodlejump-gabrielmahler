package doodlejump;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.layout.Pane;


/**
 * Class doodle sets up a new doodle, and defines methods that move the doodle on the screen up, down and sideways,
 * and a method that removes the doodle from the screen.
 */
public class Doodle {
    Ellipse _body;
    Pane gamePane;

    public Doodle(Pane pane) { // constructor of doodle takes in a pane, to which the doodle is going to be added
        gamePane = pane;
        // creating new doodle - _body, and setting its size, initial position and color
        _body = new Ellipse();
        _body.setCenterY(Constants.DOODLE_INITIAL_Y);
        _body.setCenterX(Constants.DOODLE_INITIAL_X);
        _body.setRadiusX(Constants.DOODLE_BODY_WIDTH);
        _body.setRadiusY(Constants.DOODLE_BODY_HEIGHT);
        _body.setFill(Color.GREEN);

        gamePane.getChildren().add(_body); // adding doodle to the pane/screen
    }

    public void doodleMoveLeft() { // a method that mooves doodle to the left
        double currentX;
        currentX = _body.getCenterX(); // gets doodle's current position
        // if/else statement checks if doodle isn't on the edge of the screen
        if (currentX > Constants.DOODLE_BODY_WIDTH) { // if isn't
            _body.setCenterX(currentX - Constants.DOODLE_MOVE_X); // sets doodle new position
        }
        else { // if is on the edge
            _body.setCenterX(Constants.GAME_PANE_WIDTH); // sets doodle to the right side of the screen
        }
    }

    public void doodleMoveRight() { // moves doodle to the right
        double currentX = _body.getCenterX(); // gets current position of doodle
        if (currentX < Constants.GAME_PANE_WIDTH) { // if doodle isn't on the right edge of the screen
            _body.setCenterX(currentX + Constants.DOODLE_MOVE_X); // move doodle
        }
        else{ // if it is on the edge
            _body.setCenterX(1); // move doodle to the other - left edge of the screen
        }
    }

    public void doodleYMove(double currentVelocity) { // moves doodle vertically taking in the current velocity
        double currentPos = _body.getCenterY(); // gets doodle's position
        _body.setCenterY(currentPos + currentVelocity*0.02); // set's new position based on the velocity
    }

    public void removeDoodle(Pane gamepane) { // function that removes doodle from the screen at the end of the game
        gamepane.getChildren().remove(_body); // removes _body from pane
    }


}
