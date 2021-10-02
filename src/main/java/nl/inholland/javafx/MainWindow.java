package nl.inholland.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import javax.print.attribute.standard.SheetCollate;

public class MainWindow {
    private Stage window;
    Scene scene;
    TableView<Person> tableView;
    Button addButton;
    Button deleteButton;
    TextField lastNameInput;
    TextField firstNameInput;

    public MainWindow(String username) {
        Database db = new Database();
        ObservableList<Person> people = FXCollections.observableArrayList(db.getPeople());

        //setup the window
        window = new Stage();
        window.setHeight(400);
        window.setWidth(800);
        window.setMinWidth(250);
        window.setTitle("People Manager - Logged in as: " + username);

        //add layout node and controls
        tableView = new TableView<>();

        firstNameInput = new TextField();
        firstNameInput.setPromptText("First name");

        lastNameInput = new TextField();
        lastNameInput.setPromptText("Last name");

        DatePicker birthdateInput = new DatePicker();
        birthdateInput.setPromptText("Birth date");

        addButton = new Button("Add");
        deleteButton = new Button("Delete");

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(firstNameInput, lastNameInput, birthdateInput,
                addButton, deleteButton);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(tableView, hBox);

        //create and set table columns to tableView
        TableColumn<Person, String> firstNameColumn = new TableColumn<>("First name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Person, String> lastNameColumn = new TableColumn<>("Last name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Person, String> birthDateColumn = new TableColumn<>("Date of Birth");
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

        tableView.getColumns().addAll(firstNameColumn, lastNameColumn, birthDateColumn);
        tableView.setItems(people);

        //create events for add and delete button
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Person person = new Person(firstNameInput.getText(), lastNameInput.getText(), birthdateInput.getValue());
                people.add(person);

                firstNameInput.clear();
                lastNameInput.clear();
                birthdateInput.getEditor().clear();
            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                people.remove(tableView.getSelectionModel().getSelectedItem());
            }
        });

        //create the scene and show the window
        scene = new Scene(vBox);
//        JMetro jMetro = new JMetro(Style.DARK);
//        jMetro.setScene(scene);
        styleWindow();
        window.setScene(scene);
        window.show();
    }

    private void styleWindow() {
        //FIXME: Stylesheet not working
        scene.getStylesheets().add("css/style.css");
        tableView.getStylesheets().add("tableView");
        addButton.getStylesheets().add("btnAdd");

        //inline-styling
//        addButton.setStyle(
//                        "-fx-background-color: slateblue;" +
//                        "-fx-text-fill: white;"
//        );
    }
}
