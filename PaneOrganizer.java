package doodlejump;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class PaneOrganizer {
    public BorderPane _root;
    public Pane rectsPane;

    /**
     * PaneOrganizer sets up two panes - _root, which displays the quit button, and rectsPane, which displays
     * the game and its objects
     */
    public PaneOrganizer() {
        // setting up the _root
        _root = new BorderPane();
        _root.setStyle("-fx-background-color: gray;");

        rectsPane = new Pane(); // creating new rectsPane
        rectsPane.setPrefSize(Constants.GAME_PANE_WIDTH, Constants.GAME_PANE_HEIGHT);
        rectsPane.setStyle("-fx-background-color: white");
        _root.setTop(rectsPane); // adding rectsPane on the top of _root
        new Game(rectsPane, _root); // creating new Game(), using the two new panes as arguments
    }

    public BorderPane getRoot() {
        return _root;
    } // a getter method for _root

}
