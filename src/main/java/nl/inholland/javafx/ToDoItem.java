package nl.inholland.javafx;

import java.io.Serializable;

public class ToDoItem implements Serializable {
    private String description;
    private boolean complete;

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean getComplete() { return complete; }
    public void setComplete(boolean complete) { this.complete = complete; }

    public ToDoItem(String description, boolean complete) {
        this.description = description;
        this.complete = complete;
    }
}
