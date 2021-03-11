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

public class Game {
    double currentVelocity;
    double currentYPosition;
    Doodle _doodle;

    Platform[] _arrayPlatforms;

    Platform _platform; // gonna have to be deleted and changed as a part of an array

    double randomDifferencePlatforms;

    boolean contactWithPlatform;


    public Game(Pane gamePane, BorderPane buttonPane) {
        this.setupTimeline(true); // true can be removed if unecessary
        _doodle = new Doodle(gamePane);
        gamePane.addEventHandler(KeyEvent.KEY_PRESSED, new KeyHandler());
        gamePane.setFocusTraversable(true);
        this.setupQuitButton(buttonPane);
        this.createPlatforms(gamePane);
        contactWithPlatform = false;

        currentYPosition = Constants.DOODLE_INITIAL_Y;
        currentVelocity = Constants.REBOUND_VELOCITY;

    }

    public void createPlatforms(Pane gamePane) {
        _arrayPlatforms = new Platform[Constants.PLATFORM_MAX_NO];
        double prevPlatformY = 0;
        double prevPlatformX = 0;
        for (int i = 0; i < _arrayPlatforms.length; i++) {
            if (i == 0) {
                prevPlatformX = (int)(Math.random()*(Constants.GAME_PANE_WIDTH - Constants.PLATFORM_WIDTH/2)+Constants.PLATFORM_WIDTH/2);
                prevPlatformY = Constants.DOODLE_INITIAL_Y;
            }
            else {
                prevPlatformX = _arrayPlatforms[i-1]._rectangle.getX();
                prevPlatformY = _arrayPlatforms[i-1]._rectangle.getY();
                System.out.println("new plat");
            }
            _arrayPlatforms[i] = new Platform(gamePane, prevPlatformY, prevPlatformX);
        }
    }


    public void setupTimeline(boolean OnOrOff) { // OnOrOff boolean - false -> pauses the timeline
        KeyFrame kf = new KeyFrame(Duration.millis(30), new TimeHandler()); //useing 20 milliseconds as a duration of one frame
        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);

        if (OnOrOff) {
            timeline.play(); // playing the timeline
        } else {
            timeline.stop(); // stoping the timeline
        }
    }

    public void checkContactWPlatform(Platform[] platforms) {
        for (int i = 0; i < platforms.length; i++) {
            if (_doodle._body.getBoundsInParent().intersects((platforms[i]._rectangle.getBoundsInParent()))) {
                contactWithPlatform = true;
                break;
            }
            else{
                contactWithPlatform = false;
            }
        }

    }

    private class TimeHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            checkContactWPlatform(_arrayPlatforms);

            if (contactWithPlatform) {
                currentVelocity = Constants.REBOUND_VELOCITY;
                System.out.println("contact");
            }
            if (_doodle._body.getCenterY() > Constants.DOODLE_INITIAL_Y) {
                currentVelocity = Constants.REBOUND_VELOCITY;
//                System.out.println("true");
            }
            currentVelocity = currentVelocity + Constants.GRAVITY*0.02;
            currentYPosition = currentYPosition + currentVelocity*0.02;
            _doodle.doodleYMove(currentYPosition);
//            System.out.println(currentYPosition);
            if (currentVelocity == 0) {
                System.out.println(currentYPosition);
            }
        }
    }

    private class KeyHandler implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            KeyCode keyPressed = event.getCode();
            switch (keyPressed){
                case LEFT:
                    _doodle.doodleMoveLeft();
                    System.out.println("moveleft");
                    break;
                case RIGHT:
                    _doodle.doodleMoveRight();
                    System.out.println("moveright");
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
