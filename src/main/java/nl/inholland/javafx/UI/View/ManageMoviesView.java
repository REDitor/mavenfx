package nl.inholland.javafx.UI.View;

import javafx.scene.layout.VBox;
import nl.inholland.javafx.Data.Database;

public class ManageMoviesView implements View{
    Database db;

    public ManageMoviesView(Database db) {
        this.db = db;
    }

    @Override
    public VBox getView() {
        return new VBox();
    }

    @Override
    public void assignSections() {

    }

    @Override
    public void styleView() {

    }

    @Override
    public void refreshView() {

    }

    @Override
    public void setEventHandlers() {

    }
}
