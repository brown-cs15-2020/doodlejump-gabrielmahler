package doodlejump;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Platform {
    Rectangle _rectangle;

    /**
     * Platform class creates the platfroms - object doodle can jump off, and defines methods, that can move
     * the platforms down and remove them from the game
     */
    public Platform(Pane gamepane, double prevYLoc, double prevXLoc) { // taking in pane, to which the platform should be added to, and XY coordinates, to which a new platform should be added to
        // setting up the new platform
        _rectangle = new Rectangle();
        _rectangle.setWidth(Constants.PLATFORM_WIDTH);
        _rectangle.setHeight(Constants.PLATFORM_HEIGHT);
        _rectangle.setFill(Color.BLACK);
        // setting the platform to the requested spot
        _rectangle.setX(prevXLoc);
        _rectangle.setY(prevYLoc);
        // adding the platform to the game
        gamepane.getChildren().add(_rectangle);

    }

    public double getLocY() {
        return _rectangle.getY();
    } // getter method that return platform's Y coordinate

    public void moveDown(double currentVelocity) { // method that moves platform down based on the currentVelocity
        double currentPosition = _rectangle.getY(); // getting current position of the platform
        _rectangle.setY(currentPosition - currentVelocity*0.02); // setting it to a new position
    }

    // method that removes platform from the screen
    public void removePlatform(Pane gamepane) {
        gamepane.getChildren().remove(_rectangle);
    }




}
