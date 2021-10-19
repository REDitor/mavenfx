package nl.inholland.javafx.UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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

import javax.swing.text.View;

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
    Menu userMenu, adminMenu, helpMenu, logoutMenu;
    MenuItem manageShowingsItem, manageMoviesItem, aboutItem, logoutItem, sellTicketsItem;
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
        window.setHeight(650);
        window.setWidth(1285);
        window.setTitle("Fabulous Cinema -- -- Purchase Tickets -- username: " + loggedUser.getUsername());

        //Create elements based on user permission
        instantiateMenu(loggedUser);



        //initialize ticketView as main page/scene
        vbxTicketView = ticketView.getView();
        vbxManageShowingsView = manageShowingsView.getView();
        vbxManageMoviesView = manageMoviesView.getView();
        vbxTicketView.setPadding(new Insets(10));
        vbxManageShowingsView.setPadding(new Insets(10));
        vbxManageMoviesView.setPadding(new Insets(10));
        hideViews();

        vbxTicketView.setVisible(true);

        // addToMainContainer(vbxTicketView);

        vbxMainContainer = new VBox();
        vbxMainContainer.getChildren().addAll(menuBar, vbxTicketView, vbxManageMoviesView, vbxManageShowingsView);

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

    // private void addToMainContainer(Node node) {
    //     if (!vbxMainContainer.getChildren().contains(node)) {
    //         vbxMainContainer.getChildren().add(node);
    //     }
    // }

    private void hideViews() {
        vbxTicketView.setVisible(false);
        vbxManageMoviesView.setVisible(false);
        vbxManageShowingsView.setVisible(false);
    }

    private void setEventHandlers(Stage window) {
        if (manageShowingsItem != null) {
            manageShowingsItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    hideViews();
                    vbxManageShowingsView = manageShowingsView.getView();
                    // addToMainContainer(vbxManageShowingsView);
                    vbxManageMoviesView.setVisible(true);
                }
            });
        }

        if (manageShowingsItem != null) {
            manageMoviesItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    hideViews();
                    vbxManageMoviesView = manageMoviesView.getView();
                    vbxManageMoviesView.setVisible(true);
                    // addToMainContainer(vbxManageMoviesView);
                }
            });
        }



        sellTicketsItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                hideViews();
                vbxTicketView = ticketView.getView();
                vbxTicketView.setVisible(true);
            }
        });
    }

    private void styleWindow() {
        JMetro jMetro = new JMetro(Style.DARK);
        jMetro.setScene(scene);
        scene.getStylesheets().add("css/style.css");
        menuBar.setId("menu");
    }
    //endregion

}
