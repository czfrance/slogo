package slogo.View;

// import static cellsociety.view.CellSocietyView.DEFAULT_RESOURCE_PACKAGE;

import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Creates a splash screen that allows the user to select a language before displaying the full
 * application
 *
 * @author Robert Cranston
 */

public class OpeningWindow {

    Pane myPane;
    ResourceBundle myResources;
    private String language;
    private Label myWelcome;
    private Label selectLang;
    private ChoiceBox myLangChoiceBox;
    private VBox myContainer;

    public OpeningWindow(ResourceBundle resources) {
        myPane = new StackPane();
        myContainer = new VBox();
        myResources = resources;

        createScreen();
    }

    private void createScreen() {
        language = "English";
        createElements("English");
        myPane.getChildren().add(myContainer);
    }

    private void createElements(String val) {
        myContainer.getChildren().clear();
        myWelcome = new Label(myResources.getString("Welcome"));
        selectLang = new Label(myResources.getString("SelectLanguage"));
        myWelcome.setId("welcomeText");
        myContainer.setId("welcomeScreen");
        myContainer.getChildren().addAll(myWelcome, selectLang, myLangChoiceBox);
    }


    public Pane getPane() {
        return myPane;
    }

    public ResourceBundle getMyResources() {
        return myResources;
    }
}
