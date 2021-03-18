package doodlejump;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.util.ArrayList;

public class Game {
    double currentVelocity;
    double currentYPosition;
    Doodle _doodle;
    ArrayList<Platform> _arrayPlatforms;
    Pane gameplayPane;
    GameOverRect _gameOverRect;
    Platform topPlatform;
    double moveTo;
    int leftOrRight;
    boolean topPlatformOutOfBounds;
    int topPlatNumber;
    Timeline timeline;
    boolean contactWithPlatform;


    /**
     * Top Level class, that contains most of the game logic, sets up a new doodle, platforms and eventhandlers
     */
    public Game(Pane gamePane, BorderPane buttonPane) {
        this.setupTimeline(); // sets up a new timeline
        _arrayPlatforms = new ArrayList<>(); // creates new arraylist, to which platforms will be added
        gameplayPane = gamePane; // assigning the pane which is used as a argument to a local pane
        _doodle = new Doodle(gamePane); // creates new doodle and adds it to the pane
        gamePane.addEventHandler(KeyEvent.KEY_PRESSED, new KeyHandler()); // creates new keyHandler
        gamePane.setFocusTraversable(true);
        this.setupQuitButton(buttonPane); // creates the quit button
        contactWithPlatform = false; // sets up boolean for later use
        currentYPosition = Constants.DOODLE_INITIAL_Y;
        currentVelocity = 0; // sets up initial velocity of the doodle
        topPlatformOutOfBounds = false;
        // adds the first platform, from which the doodle will bounce off as first
        _arrayPlatforms.add(new Platform(gameplayPane, Constants.DOODLE_INITIAL_Y+Constants.DOODLE_BODY_HEIGHT/2+10, Constants.DOODLE_INITIAL_X));

    }

    public void setupTimeline() { // sets up a new timeline
        KeyFrame kf = new KeyFrame(Duration.millis(20), new TimeHandler()); //using 20 milliseconds as a duration of one frame
        timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play(); // playing the timeline


    }

    public void checkContactWPlatform(ArrayList<Platform> platforms) { // method that checks if the doodle has intersected any of the platforms - if yes, returns true, if not false
        for (int i = 0; i < platforms.size() - 1; i++) { // checks for every platfrom in the array list
            if (_doodle._body.getBoundsInParent().intersects((platforms.get(i)._rectangle.getBoundsInParent()))) { // if doodle intersects platform
                contactWithPlatform = true;
                break;

            }
            else{
                contactWithPlatform = false;
            }
        }

    }

    public double findNewXLoc(double prevLoc){ // method that randomly finds a x coordinate for a new platform
        if(Math.random()>0.5) { // decides, whether the platform should be left or right from the previous platform
            leftOrRight = -1;
        }
        else {
            leftOrRight = 1;
        }
        moveTo = leftOrRight * ((int)(Math.random()*Constants.PLATFORM_X_SPAWN_DIFF)); // finds the new difference between new and old platform
        if(moveTo + prevLoc >= (Constants.GAME_PANE_WIDTH - Constants.PLATFORM_WIDTH/2) || moveTo + prevLoc < 0) { // checks if the new location is on screen
            moveTo = moveTo * -1; // if not, changes the position to the other side
        }
        return moveTo+prevLoc;
    }

    public double findNewYLoc(double prevLoc) { // finds new Y location of a platform in a given interval
        double randomDifferencePlatforms = ((int) (Math.random() * (Constants.PLATFORM_Y_SPAWN_MAX - Constants.PLATFORM_Y_SPAWN_MIN) + Constants.PLATFORM_Y_SPAWN_MIN));
        return (prevLoc - randomDifferencePlatforms); // adds the new difference to the location of the previous platform
    }


    private class TimeHandler implements EventHandler<ActionEvent> { // timehandler, which vertically moves the platforms and doodle
        @Override
        public void handle(ActionEvent event) {
            topPlatNumber = _arrayPlatforms.size()-1; // number of the last added platform
            while (_arrayPlatforms.get(topPlatNumber).getLocY() > 0) { // checks if the top platform is on the screen
                // adds new platform to the array list
                int numberOfPreviousTopPlatform = _arrayPlatforms.size()-1;
                _arrayPlatforms.add(new Platform(gameplayPane, findNewYLoc(_arrayPlatforms.get(numberOfPreviousTopPlatform)._rectangle.getY()), findNewXLoc(_arrayPlatforms.get(numberOfPreviousTopPlatform)._rectangle.getX())));
                topPlatNumber += 1;
            }
            topPlatform = _arrayPlatforms.get(topPlatNumber); // assigns the top platform to local variable
            currentVelocity = currentVelocity + Constants.GRAVITY*0.02;

            if(currentVelocity >= 0) { // if doodle is falling
                _doodle.doodleYMove(currentVelocity); // moves doodle
                checkContactWPlatform(_arrayPlatforms); // checks if intersects with platforms
                if(contactWithPlatform) { // if yes, sets doodle to the rebound velocity
                    currentVelocity = Constants.REBOUND_VELOCITY;
                }
                if (_doodle._body.getCenterY() > Constants.GAME_PANE_HEIGHT+Constants.DOODLE_BODY_HEIGHT) { // if doodle falls out of screen, ends game
                    gameOver();

                }
            }
            else{
                if(_doodle._body.getCenterY() <= Constants.GAME_PANE_HEIGHT/2) { // if doodle gets to the midpoint
                    for (int i = 0; i < _arrayPlatforms.size(); i++) { // moves the platforms down instead of the doodle
                        _arrayPlatforms.get(i).moveDown(currentVelocity);
                    }
                }
                else {
                    _doodle.doodleYMove(currentVelocity); // if is not in midpoint, moves doodle up
                }
            }

            if (_arrayPlatforms.get(0).getLocY() > Constants.GAME_PANE_HEIGHT) { // if platform is out of the screen, removes it
                _arrayPlatforms.get(0).removePlatform(gameplayPane);
                _arrayPlatforms.remove(0);

            }
        }
    }

    private class KeyHandler implements EventHandler<KeyEvent> { // key handler for moving the doodle left and right
        @Override
        public void handle(KeyEvent event) {
            KeyCode keyPressed = event.getCode();
            switch (keyPressed){
                case LEFT:
                    _doodle.doodleMoveLeft(); // moves left if the key pressed is left arrow
                    break;
                case RIGHT:
                    _doodle.doodleMoveRight(); // moves right if the key pressed is right arrow
                    break;
                default:
                    break;
            }
            event.consume();
        }
    }

    private class quitHandler implements EventHandler<ActionEvent> { // quits the program if the button is pressed
        @Override
        public void handle(ActionEvent event) {
            System.exit(0);
        }
    }

    private void setupQuitButton(BorderPane pane) { // sets up the quit button
        Button b1 = new Button("Quit");
        b1.setOnAction(new quitHandler());
        pane.setCenter(b1); // adds it to the argumented pane
    }

    public void gameOver() { // method that ends the game - stops timeline, add the GameOverRect, quits keyhandler and removes doodle
        timeline.stop();
        _gameOverRect = new GameOverRect(gameplayPane);
        gameplayPane.removeEventHandler(KeyEvent.KEY_PRESSED, new KeyHandler());
        _doodle.removeDoodle(gameplayPane);
    }
}
