package slogo;

import java.awt.*;
import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import org.junit.jupiter.api.BeforeEach;
import slogo.View.OpeningWindow;


class OpeningWindowTest {
    public static final String DEFAULT_RESOURCE_PACKAGE = "/slogo.languages/";
    private static final String DARK_BACKGROUND = "-fx-background-color: BLACK";
    private static final String LIGHT_BACKGROUND = "-fx-background-color: BEIGE";
    private String STYLESHEET;

    private ResourceBundle myResources;
    private BorderPane myRoot;

    private OpeningWindow myWelcome;
    private int currentGridY;
    private int currentGridX;
    public static final Dimension DEFAULT_SIZE = new Dimension(800, 600);

    @BeforeEach
    Scene setup() {
        myRoot.getChildren().clear();
        myWelcome = new OpeningWindow(myResources);
        myRoot.setCenter(myWelcome.getPane());
        Button proceed = SlogoView.makeButton("Go", event -> setup(),
                myResources);
        myRoot.setBottom(proceed);
        currentGridY = 0;
        currentGridX = 0;
        Scene scene = new Scene(myRoot, DEFAULT_SIZE.height, DEFAULT_SIZE.width);
        scene.getStylesheets()
                .add(getClass().getResource("/welcome.css").toExternalForm());
        return scene;
    }
}

