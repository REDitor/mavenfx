package nl.inholland.javafx;

enum AccessLevel { Basic, Editor, Admin}

public abstract class User {
    private String username;
    private String password;
    private AccessLevel access;
}
