package doodlejump;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class PaneOrganizer {
    public BorderPane _root;
    public Pane rectsPane;

    public PaneOrganizer() {
        _root = new BorderPane();
        _root.setStyle("-fx-background-color: gray;");
        rectsPane = new Pane();
        rectsPane.setPrefSize(Constants.GAME_PANE_WIDTH, Constants.GAME_PANE_HEIGHT);
        rectsPane.setStyle("-fx-background-color: white");
        _root.setTop(rectsPane);
        new Game(rectsPane, _root);
    }

    // creating the "game" pane
    public void createRectsPane(){
        rectsPane = new Pane();
        rectsPane.setPrefSize(Constants.GAME_PANE_WIDTH, Constants.GAME_PANE_HEIGHT);
        rectsPane.setStyle("-fx-background-color: white");
        _root.setTop(rectsPane);
    }

    public BorderPane getRoot() {
        return _root;
    }

}
