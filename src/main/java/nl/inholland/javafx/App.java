package nl.inholland.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage window) throws Exception {
        window.setHeight(600);
        window.setWidth(800);
        window.setTitle("Dead Puppies");

        BorderPane pane = new BorderPane();
        Label hello = new Label("Hello World");
        pane.setCenter(hello);

        Scene scene = new Scene(pane);
        window.setScene(scene);
        window.show();
    }
}
