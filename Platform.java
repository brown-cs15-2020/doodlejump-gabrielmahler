package doodlejump;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Platform {
    Rectangle _rectangle;
    int leftOrRight;
    double moveTo;
    double locX;
    double locY;


    public Platform(Pane gamepane, double prevYLoc, double prevXLoc) {
        _rectangle = new Rectangle();
        _rectangle.setWidth(Constants.PLATFORM_WIDTH);
        _rectangle.setHeight(Constants.PLATFORM_HEIGHT);
        _rectangle.setFill(Color.BLACK);
        _rectangle.setX(prevXLoc);
        _rectangle.setY(prevYLoc);
        gamepane.getChildren().add(_rectangle);

//        gamePane.getChildren().add(_rectangle);
    }

    public double findNewXLoc(double prevLoc){
        if((int)(Math.random())>0.5) {
            leftOrRight = -1;
        }
        else {
            leftOrRight = 1;
        }
        moveTo = prevLoc + leftOrRight * (int)(Math.random()*Constants.PLATFORM_X_SPAWN_DIFF);
        if(moveTo <= Constants.PLATFORM_WIDTH/2 || moveTo >= Constants.GAME_PANE_WIDTH - Constants.PLATFORM_WIDTH/2) {
            moveTo = moveTo * -1;
        }
        System.out.println("new x: " + moveTo);
        return moveTo;

    }

    public double findNewYLoc(double prevLoc) {
        double randomDifferencePlatforms = ((int) (Math.random() * (Constants.PLATFORM_Y_SPAWN_MAX - Constants.PLATFORM_Y_SPAWN_MIN) + Constants.PLATFORM_Y_SPAWN_MIN));
        return (prevLoc - randomDifferencePlatforms);

    }

    public double getLocX() {
        return _rectangle.getLayoutX();
    }
    public double getLocY() {
        return _rectangle.getY();
    }

    public void moveDown(double currentVelocity) {
        double currentX = _rectangle.getLayoutX();
        double currentPosition = _rectangle.getY();
        _rectangle.setY(currentPosition - currentVelocity*0.02);
        System.out.println(_rectangle.getY());

    }




}
