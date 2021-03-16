package doodlejump;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
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
    Platform testingPlatform;

    double previousPlatformX;
    double previousPlatformY;

    ArrayList<Platform> _arrayPlatforms;

    Pane gameplayPane;

    Platform topPlatform;

    double moveTo;
    int leftOrRight;

    boolean topPlatformOutOfBounds;
    int topPlatNumber;




    boolean contactWithPlatform;


    public Game(Pane gamePane, BorderPane buttonPane) {

        this.setupTimeline(true); // true can be removed if unecessary
        _arrayPlatforms = new ArrayList<>();

        // working code
        gameplayPane = gamePane;
        _doodle = new Doodle(gamePane);
        gamePane.addEventHandler(KeyEvent.KEY_PRESSED, new KeyHandler());
        gamePane.setFocusTraversable(true);
        this.setupQuitButton(buttonPane);
        contactWithPlatform = false;
        currentYPosition = Constants.DOODLE_INITIAL_Y;
        currentVelocity = 0; // initial velocity i think
        topPlatformOutOfBounds = false;

        _arrayPlatforms.add(new Platform(gameplayPane, Constants.DOODLE_INITIAL_Y+Constants.DOODLE_BODY_HEIGHT/2+10, Constants.DOODLE_INITIAL_X));

    }

//    public void createPlatforms(Pane gamePane) {
//        _arrayPlatforms = new ArrayList<>();
//        double prevPlatformY;
//        double prevPlatformX;
//        for (int i = 0; i < Constants.PLATFORM_MAX_NO; i++) {
//            if (i == 0) {
//                prevPlatformX = (int)(Math.random()*(Constants.GAME_PANE_WIDTH - Constants.PLATFORM_WIDTH/2)+Constants.PLATFORM_WIDTH/2);
//                prevPlatformY = Constants.DOODLE_INITIAL_Y;
//            }
//            else {
//                int n = i-1;
//                prevPlatformX = _arrayPlatforms.get(n).getLocX();
//                prevPlatformY = _arrayPlatforms.get(n).getLocY();
//            }
//            _arrayPlatforms.add(new Platform(gamePane, prevPlatformY, prevPlatformX));
//        }
//    }


    public void setupTimeline(boolean OnOrOff) { // OnOrOff boolean - false -> pauses the timeline
        KeyFrame kf = new KeyFrame(Duration.millis(20), new TimeHandler()); //useing 20 milliseconds as a duration of one frame
        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);

        if (OnOrOff) {
            timeline.play(); // playing the timeline
        } else {
            timeline.stop(); // stoping the timeline
        }
    }

    public void checkContactWPlatform(ArrayList<Platform> platforms) {
        for (int i = 0; i < platforms.size() - 1; i++) {
            if (_doodle._body.getBoundsInParent().intersects((platforms.get(i)._rectangle.getBoundsInParent()))) {
                contactWithPlatform = true;
                break;

            }
            else{
                contactWithPlatform = false;
            }
        }

    }

    public double findNewXLoc(double prevLoc){
        if(Math.random()>0.5) {
            leftOrRight = -1;
        }
        else {
            leftOrRight = 1;
        }
        moveTo = leftOrRight * ((int)(Math.random()*Constants.PLATFORM_X_SPAWN_DIFF));
//        System.out.println(moveTo);
        if(moveTo + prevLoc >= (Constants.GAME_PANE_WIDTH - Constants.PLATFORM_WIDTH/2) || moveTo + prevLoc < 0) {
            moveTo = moveTo * -1;
        }

        return moveTo+prevLoc;

    }

    public double findNewYLoc(double prevLoc) {
        double randomDifferencePlatforms = ((int) (Math.random() * (Constants.PLATFORM_Y_SPAWN_MAX - Constants.PLATFORM_Y_SPAWN_MIN) + Constants.PLATFORM_Y_SPAWN_MIN));
        return (prevLoc - randomDifferencePlatforms);
    }

    public boolean isTopPlatOnScreen(Platform platform) {
        if (platform._rectangle.getY() < 0){
            return false;
        }
        else{
            return true;
        }
    }


    private class TimeHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {

            /**
             * working code bellow
             */



            /**
             * 2nd attempt
             */
            topPlatNumber = _arrayPlatforms.size() - 1;
            System.out.println(_arrayPlatforms.get(topPlatNumber).getLocY());

//            topPlatform = _arrayPlatforms.get(_arrayPlatforms.size());
//            topPlatformOutOfBounds = isTopPlatOnScreen(_arrayPlatforms.get(_arrayPlatforms.size()));
            while (_arrayPlatforms.get(topPlatNumber).getLocY() > 0) {
                int numberOfPreviousTopPlatform = _arrayPlatforms.size() - 1;
                _arrayPlatforms.add(new Platform(gameplayPane, findNewYLoc(_arrayPlatforms.get(numberOfPreviousTopPlatform)._rectangle.getY()), findNewXLoc(_arrayPlatforms.get(numberOfPreviousTopPlatform)._rectangle.getX())));
                topPlatNumber += 1;
            }
            topPlatform = _arrayPlatforms.get(topPlatNumber);

            checkContactWPlatform(_arrayPlatforms);
            if(contactWithPlatform) {
                currentVelocity = Constants.REBOUND_VELOCITY;
            }

            currentVelocity = currentVelocity + Constants.GRAVITY*0.02;
            currentYPosition = currentYPosition + currentVelocity*0.02;

//            _doodle.doodleYMove(currentVelocity);

            if(currentVelocity >= 0) {
                _doodle.doodleYMove(currentVelocity);
            }
            else{
                if(_doodle._body.getCenterY() <= Constants.GAME_PANE_HEIGHT/2) {
                    for (int i = 0; i < _arrayPlatforms.size()-1; i++) {
                        _arrayPlatforms.get(i).moveDown(currentVelocity);
                    }
                }
                else {
                    _doodle.doodleYMove(currentVelocity);
                }
            }

            /**
             * 1st attempt
             */

//            if (contactWithPlatform) {
//                currentVelocity = Constants.REBOUND_VELOCITY;
//            }
//            if (_doodle._body.getCenterY() > Constants.DOODLE_INITIAL_Y) {
//                currentVelocity = Constants.REBOUND_VELOCITY;
//            }
//
//
////            _doodle.doodleYMove(currentVelocity);
//
//            if(currentVelocity >= 0) {
//                _doodle.doodleYMove(currentVelocity); // moving the doodle
//
//            }
//            else{
//                if(_doodle._body.getCenterY() >= Constants.GAME_PANE_HEIGHT/2) {
//                    _doodle.doodleYMove(currentVelocity);
//                }
//                else{
//                    for(Platform platform: _arrayPlatforms){
//                        platform.moveDown(currentVelocity);
//
//                    }
////                    System.out.println(testingPlatform._rectangle.getY());
//                    testingPlatform.moveDown(currentVelocity);
//                }
//                }
        }
    }

    private class KeyHandler implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            KeyCode keyPressed = event.getCode();
            switch (keyPressed){
                case LEFT:
                    _doodle.doodleMoveLeft();
                    break;
                case RIGHT:
                    _doodle.doodleMoveRight();
                    break;
                default:
                    break;
            }
            event.consume();
        }
    }

    private class quitHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            System.exit(0);
        }
    }

    private void setupQuitButton(BorderPane pane) {
        Button b1 = new Button("Quit");
        b1.setOnAction(new quitHandler());
        pane.setCenter(b1);
    }
}
