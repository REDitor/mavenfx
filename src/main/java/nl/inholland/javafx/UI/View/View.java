package nl.inholland.javafx.UI.View;

import javafx.scene.layout.VBox;

public interface View {
    VBox getView();
    void assignSections();
    void styleView();
    void setEventHandlers();
}
