package nl.inholland.javafx.UI.View;

import javafx.scene.layout.HBox;
import nl.inholland.javafx.Data.Database;

public class ManageMoviesView {
    Database db;

    public ManageMoviesView(Database db) {
        this.db = db;
    }

    public HBox getView() {
        HBox hBox = new HBox();



        return hBox;
    }
}
