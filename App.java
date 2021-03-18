package doodlejump;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class APP instantiates the top level object, sets up the scene and shows it on the screen
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Instantiating top-level object
        PaneOrganizer organizer = new PaneOrganizer();
        // setting up scene
        Scene scene = new Scene(organizer.getRoot());
        primaryStage.setWidth(Constants.GAME_PANE_WIDTH);
        primaryStage.setHeight(Constants.GAME_PANE_HEIGHT + 120);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Doodle Jump");
        // showing scene
        primaryStage.show();
    }

    public static void main(String[] argv) {
        // launch is a static method inherited from Application.
        launch(argv);
    }
}
