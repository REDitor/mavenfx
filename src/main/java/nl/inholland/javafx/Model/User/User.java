package nl.inholland.javafx.Model.User;

public class User {
    protected String username;
    protected String password;
    protected Permission permission;

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Permission getPermission() { return permission; }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.permission = Permission.Basic;
    }
}