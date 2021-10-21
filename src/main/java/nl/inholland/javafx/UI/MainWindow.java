package nl.inholland.javafx.UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import nl.inholland.javafx.Data.Database;
import nl.inholland.javafx.Model.User.Permission;
import nl.inholland.javafx.Model.User.User;
import nl.inholland.javafx.UI.View.ManageMoviesView;
import nl.inholland.javafx.UI.View.ManageShowingsView;
import nl.inholland.javafx.UI.View.TicketView;
import nl.inholland.javafx.UI.View.View;

public class MainWindow {
    Database db;
    User loggedUser;

    //region Views
    View ticketView;
    // View manageMoviesView;
    View manageShowingsView;
    //endregion

    //region Elements
    //Container(s)
    VBox vbxMainContainer;
    VBox vbxTicketView;
    // VBox vbxManageMoviesView;
    VBox vbxManageShowingsView;


    //Scenes
    Scene scene;

    //Menu
    MenuBar menuBar;
    Menu userMenu, adminMenu, helpMenu, logoutMenu;
    MenuItem manageShowingsItem, manageMoviesItem, aboutItem, logoutItem, sellTicketsItem;
    //endregion

    public MainWindow(Database db, User loggedUser) {
        this.db = db;
        this.loggedUser = loggedUser;

        Stage window = new Stage();

        ticketView = new TicketView(db, window);
        // manageMoviesView = new ManageMoviesView(db, window);
        manageShowingsView = new ManageShowingsView(db, window);

        loadWindow(window);
    }

    //region Initiate Window
    private void loadWindow(Stage window) {
        window.sizeToScene();

        //Create elements based on user permission
        instantiateMenu(loggedUser);

        //initialize ticketView as main page/scene

        vbxTicketView = ticketView.getView();
        vbxManageShowingsView = manageShowingsView.getView();

        // vbxManageMoviesView = manageMoviesView.getView();
        vbxMainContainer = new VBox();
        vbxMainContainer.getChildren().addAll(menuBar, vbxTicketView, vbxManageShowingsView);

        showSelectedView(vbxTicketView);

        scene = new Scene(vbxMainContainer);
        styleWindow();

        setEventHandlers(window);

        //Display elements, scene and window
        window.setScene(scene);
        window.show();
    }

    private void instantiateMenu(User loggedUser) {
        menuBar = new MenuBar();
        sellTicketsItem = new MenuItem("Sell Tickets");

        if (loggedUser.getPermission() == Permission.Admin) {
            adminMenu = new Menu("Admin");

            manageShowingsItem = new MenuItem("Manage Showings");
            manageMoviesItem = new MenuItem("Manage Movies");

            adminMenu.getItems().addAll(sellTicketsItem, manageShowingsItem, manageMoviesItem);
            menuBar.getMenus().add(adminMenu);
        } else {
            userMenu = new Menu("User");
            userMenu.getItems().add(sellTicketsItem);
            menuBar.getMenus().add(userMenu);
        }

        helpMenu = new Menu("Help");
        aboutItem = new MenuItem("About");

        logoutMenu = new Menu("Logout");
        logoutItem = new MenuItem("Logout...");

        helpMenu.getItems().add(aboutItem);
        logoutMenu.getItems().add(logoutItem);
        menuBar.getMenus().addAll(helpMenu, logoutMenu);
    }

    private void hideViews() {
        vbxTicketView.setVisible(false);
        vbxTicketView.setManaged(false);
        // vbxManageMoviesView.setVisible(false);
        //...
        vbxManageShowingsView.setVisible(false);
        vbxManageShowingsView.setManaged(false);
    }

    private void setEventHandlers(Stage window) {
        if (manageShowingsItem != null) {
            manageShowingsItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    showSelectedView(vbxManageShowingsView);
                }
            });
        }

        // if (manageMoviesItem != null) {
        //     manageMoviesItem.setOnAction(new EventHandler<ActionEvent>() {
        //         @Override
        //         public void handle(ActionEvent actionEvent) {
        //             hideViews();
        //             vbxManageMoviesView = manageMoviesView.getView();
        //             vbxManageMoviesView.setVisible(true);
        //         }
        //     });
        // }

        sellTicketsItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showSelectedView(vbxTicketView);
            }
        });
    }

    private void showSelectedView(Node node) {
        hideViews();
        node.setManaged(true);
        node.setVisible(true);
    }

    private void styleWindow() {
        JMetro jMetro = new JMetro(Style.DARK);
        jMetro.setScene(scene);
        scene.getStylesheets().add("css/style.css");
        menuBar.setId("menu");
    }
    //endregion

}
