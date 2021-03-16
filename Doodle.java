package doodlejump;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.layout.Pane;



public class Doodle {
    Ellipse _body;

    public Doodle(Pane pane) {
        _body = new Ellipse();
        _body.setCenterY(Constants.DOODLE_INITIAL_Y);
        _body.setCenterX(Constants.DOODLE_INITIAL_X);
        _body.setRadiusX(Constants.DOODLE_BODY_WIDTH);
        _body.setRadiusY(Constants.DOODLE_BODY_HEIGHT);
        _body.setFill(Color.GREEN);
        pane.getChildren().add(_body);
    }

    public void doodleMoveLeft() {
        double currentX;
        currentX = _body.getCenterX();
        if (currentX > Constants.DOODLE_BODY_WIDTH) {
            _body.setCenterX(currentX - Constants.DOODLE_MOVE_X);
            System.out.println(currentX);
        }
        else {
            _body.setCenterX(Constants.GAME_PANE_WIDTH);
        }
    }

    public void doodleMoveRight() {
        double currentX = _body.getCenterX();
        if (currentX < Constants.GAME_PANE_WIDTH) {
            _body.setCenterX(currentX + Constants.DOODLE_MOVE_X);
        }
        else{
            _body.setCenterX(1);
        }
    }

    public void doodleYMove(double currentVelocity) {
        double currentPos = _body.getCenterY();
        _body.setCenterY(currentPos + currentVelocity*0.02);
    }


}
