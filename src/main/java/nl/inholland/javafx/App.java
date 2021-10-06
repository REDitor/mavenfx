package nl.inholland.javafx;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.plaf.InsetsUIResource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {
    @Override
    public void start(Stage window) throws Exception {
        window.setHeight(600);
        window.setWidth(300);
        window.setTitle("Dead Puppies");

        //region VBox
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(0));

        //Menu
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        MenuItem loadItem = new MenuItem("Load...");
        MenuItem saveItem = new MenuItem("Save...");
        fileMenu.getItems().addAll(loadItem, saveItem);

        Menu aboutMenu = new Menu("About");
        MenuItem aboutItem = new MenuItem("About");
        aboutMenu.getItems().add(aboutItem);

        menuBar.getMenus().addAll(fileMenu, aboutMenu);

        //Tableview
        TableView tableView = new TableView();
        ObservableList<ToDoItem> toDoList = tableView.getItems();

        //HBox
        HBox hBox = new HBox();

        TextField txtTask = new TextField();
        txtTask.setPromptText("Enter a To Do Item");
        Button btnAdd = new Button("Add");

        hBox.getChildren().addAll(txtTask, btnAdd);

        vBox.getChildren().addAll(menuBar, tableView, hBox);
        //endregion

        //region Event Handlers
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                toDoList.add(new ToDoItem(
                        txtTask.getText(),
                        false
                ));
            }
        });

        tableView.setRowFactory(tv -> {
            TableRow<ToDoItem> row = new TableRow<>();
            row.setOnMouseClicked(mouseEvent ->
            {
                if (mouseEvent.getClickCount() == 2 && (!row.isEmpty())) {
                    ToDoItem toDoItem = row.getItem();
                    toDoItem.setComplete(!toDoItem.getComplete());
                    tableView.refresh();
                    txtTask.requestFocus();
                }
            });
            return row;
        });

//        try (FileInputStream fis = new FileInputStream(new File("toDoItems.dat"));
//             ObjectInputStream ois = new ObjectInputStream(fis);) {
//            // try with resources protected code
//        } catch (FileNotFoundException fnfe) {
//            System.out.println("File not found");
//        } catch (IOException ioe) {
//            System.out.println("Another exception");
//        }// apparently EOFException also gets caught by on of the above

        loadItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Reading the list from file
                try (ObjectInputStream ois = new ObjectInputStream(
                        new FileInputStream(new File("items.dat")))) {
                    while (true) {
                        try {
                            ToDoItem item = (ToDoItem) ois.readObject();
                            toDoList.add(item);
                        }catch (EOFException eofe) {
                            break;
                        }
                    }
                }catch (FileNotFoundException fnfe) {}
                catch (ClassNotFoundException cnfe) {}
                catch (IOException ioe) {}
            }
        });

        saveItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Saving list to file
                try (FileOutputStream fos = new FileOutputStream(new File("items.dat"));
                     ObjectOutputStream oos = new ObjectOutputStream(fos);) {
                    for (ToDoItem item : toDoList) {
                        oos.writeObject(item);
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //endregion

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }
}
