package nl.inholland.javafx.Model.User;

public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
        this.permission = Permission.Admin;
    }
}