package nl.inholland.javafx.UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.inholland.javafx.Data.Database;
import nl.inholland.javafx.Model.User.Permission;
import nl.inholland.javafx.Model.User.User;
import nl.inholland.javafx.UI.View.ManageMoviesView;
import nl.inholland.javafx.UI.View.ManageShowingsView;
import nl.inholland.javafx.UI.View.TicketView;

public class MainWindow {
    Database db;
    User loggedUser;

    //region Views
    TicketView ticketView;
    ManageMoviesView manageMoviesView;
    ManageShowingsView manageShowingsView;
    //endregion

    //region Elements
    //Container(s)
    VBox vbxMainContainer;
    VBox vbxTicketView;
    VBox vbxManageMoviesView;
    VBox vbxManageShowingsView;

    //Scenes
    Scene scene;

    //Menu
    MenuBar menuBar;
    Menu adminMenu, sellTicketsMenu, helpMenu, logoutMenu;
    MenuItem manageShowingsItem, manageMoviesItem, aboutItem, logoutItem;
    //endregion

    public MainWindow(Database db, User loggedUser) {
        this.db = db;
        this.loggedUser = loggedUser;

        ticketView = new TicketView(db);
        manageMoviesView = new ManageMoviesView(db);
        manageShowingsView = new ManageShowingsView(db);

        Stage window = new Stage();
        loadWindow(window);
    }

    //region Initiate Window
    private void loadWindow(Stage window) {
        window.setHeight(700);
        window.setWidth(1280);
        window.setTitle("Fabulous Cinema -- -- Purchase Tickets -- username: " + loggedUser.getUsername());

        //Create elements based on user permission
        instantiateMenu(loggedUser);

        vbxMainContainer = new VBox();
        vbxMainContainer.getChildren().add(menuBar);

        //initialize ticketView as main page/scene
        vbxTicketView = ticketView.getView();
        vbxTicketView.setPadding(new Insets(10));
        addToContainer(vbxTicketView);

        scene = new Scene(vbxMainContainer);
        styleWindow();

        setEventHandlers(window);

        //Display elements, scene and window
        window.setScene(scene);
        window.show();
    }

    private void refreshWindow() {

    }

    private void instantiateMenu(User loggedUser) {
        menuBar = new MenuBar();

        if (loggedUser.getPermission() == Permission.Admin) {
            adminMenu = new Menu("Admin");
            manageShowingsItem = new MenuItem("Manage Showings");
            manageMoviesItem = new MenuItem("Manage Movies");

            adminMenu.getItems().addAll(manageShowingsItem, manageMoviesItem);
            menuBar.getMenus().add(adminMenu);
        }

        sellTicketsMenu = new Menu("Sell Tickets");

        helpMenu = new Menu("Help");
        aboutItem = new MenuItem("About");

        logoutMenu = new Menu("Logout");
        logoutItem = new MenuItem("Logout...");

        helpMenu.getItems().add(aboutItem);
        logoutMenu.getItems().add(logoutItem);
        menuBar.getMenus().addAll(sellTicketsMenu, helpMenu, logoutMenu);
    }

    private void addToContainer(Node node) {
        if (!vbxMainContainer.getChildren().contains(node)) {
            vbxMainContainer.getChildren().add(node);
        }
    }

    private void resetView() {
        vbxMainContainer.getChildren().removeAll();
        vbxMainContainer.getChildren().add(menuBar);
    }

    private void setEventHandlers(Stage window) {
        manageShowingsItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                resetView();
                vbxManageShowingsView = manageShowingsView.getView();
                addToContainer(vbxManageShowingsView);
                vbxManageMoviesView.setVisible(true);
                //TODO: maybe better to hide instead of removing and adding
            }
        });

        manageMoviesItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                resetView();
                vbxManageMoviesView = manageMoviesView.getView();
                addToContainer(vbxManageMoviesView);
            }
        });

        sellTicketsMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                resetView();
                vbxTicketView = ticketView.getView();
                addToContainer(vbxTicketView);
            }
        });
    }

    private void styleWindow() {
        scene.getStylesheets().add("css/style.css");
        menuBar.setId("menu");
    }
    //endregion

}
