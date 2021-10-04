package nl.inholland.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage window) throws Exception {
        window.setHeight(600);
        window.setWidth(800);
        window.setTitle("Dead Puppies");

        VBox vBox = new VBox();
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        MenuItem loadItem = new MenuItem("Load...");
        MenuItem saveItem = new MenuItem("Save...");
        fileMenu.getItems().addAll(loadItem, saveItem);

        Menu aboutMenu = new Menu("About");
        MenuItem aboutItem = new MenuItem("About");
        aboutMenu.getItems().add(aboutItem);

        TableView tableView = new TableView();

        HBox hBox = new HBox();
        TextField txtFeedTheDog = new TextField();
        txtFeedTheDog.setPromptText("Feed the Dog");

        Scene scene = new Scene();
        window.setScene(scene);
        window.show();
    }
}
