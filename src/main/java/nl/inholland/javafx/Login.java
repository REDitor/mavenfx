package nl.inholland.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login extends Application {
    @Override
    public void start(Stage window) throws Exception {
        window.setHeight(600);
        window.setWidth(320);
        window.setTitle("University Project JavaFX");



        Scene scene = new Scene();
        window.setScene(scene);
        window.show();
    }
}
