package slogo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import slogo.Console.Console;
import slogo.Model.TurtleModel;
import slogo.View.OpeningWindow;


import java.util.ResourceBundle;

import slogo.View.SimulationDisplay;
import slogo.View.SketchbookView;
import slogo.View.TurtleView;

public class SlogoView {


    /**
     * Holds all of the main view elements for the application
     *
     * <p>
     * Initializes a welcome page that first allows users to select the language that they want to use
     * for the project. After selecting and submitting a language it initializes a a simulation page
     * that holds all of the simulations and their configurations.
     * </p>
     *
     *
     * @author Thivya Sivarjah
     */

    public static final String DEFAULT_RESOURCE_PACKAGE = "/slogo.languages/";
    private static final String DARK_BACKGROUND = "-fx-background-color: BLACK";
    private static final String LIGHT_BACKGROUND = "-fx-background-color: BEIGE";
    private static final String LANGUAGE = "English";
    private String STYLESHEET;

    private ResourceBundle myResources;
    private BorderPane myRoot;

    private OpeningWindow myWelcome;
    private Console myConsole;
    private Compiler myCompiler;
    private SketchbookView mySketch;
    private HBox TitleBox;
    private ScrollPane ScrollBox;
    private HBox ScreenConfigBox;
    private Label titleText;
    private SimulationDisplay mySimulation;

    private int currentGridY;
    private int currentGridX;
    private boolean inNightMode;

    private TurtleModel myTurtleModel; // this is a temp solution

    /**
     * Initializes the starting properties need to create the initial simulation
     *
     * @param language - chooses what language file to read from between: English
     */
    public SlogoView(String language) {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
        currentGridY = 0;
        currentGridX = 0;
        myRoot = new BorderPane();
        STYLESHEET = "welcome.css";
        inNightMode = false;

        //temp solution delete later
        myTurtleModel = new TurtleModel(0,0,0);
        myCompiler = new  Compiler(language, myTurtleModel);

    }

        /**
         * Creates scene to hold all of the elements to be displayed to the user.
         *
         * @param width  - width in dixels of initial window
         * @param height - height in pixels of initial window
         * @return scene holding all elements displayed
         */
    public Scene makeScene(int width, int height) {
        displayWelcome();
        Scene scene = new Scene(myRoot, width, height);
        scene.getStylesheets()
                .add(getClass().getResource("/welcome.css").toExternalForm());
        return scene;
    }

    //Creates the initial welcome screen for the user to select a language
    private void displayWelcome() {
        myRoot.getChildren().clear();
        myWelcome = new OpeningWindow(myResources);
        myRoot.setCenter(myWelcome.getPane());
        Button proceed = SlogoView.makeButton("Go", event -> displayConsole(),
                myResources);
        myWelcome.getContainer().getChildren().addAll(proceed);
        myWelcome.getContainer().setAlignment(Pos.CENTER);
        currentGridY = 0;
        currentGridX = 0;
    }

    private void displaySketch() {
        myTurtleModel = new TurtleModel(0, 0, 90);
        myTurtleModel.addInsn("forward 100");
        myTurtleModel.addInsn("penUp");
        myTurtleModel.addInsn("back 200");
        myTurtleModel.addInsn("right 360");
        myRoot.getChildren().clear();
        setupSketch();
        myRoot.setCenter(mySimulation.getPane());
        currentGridY = 0;
        currentGridX = 0;
    }

    private void setupSketch() {
        mySketch = new SketchbookView(myTurtleModel);
        mySimulation = new SimulationDisplay(mySketch, DEFAULT_RESOURCE_PACKAGE);
//        mySketch.prefWidthProperty().bind(myRoot.widthProperty());
//        mySketch.prefHeightProperty().bind(myRoot.heightProperty());
    }

    //returns a button with the title provided linked to the event passed as a parameter
    public static Button makeButton(String property, EventHandler<ActionEvent> handler,
                                    ResourceBundle resources) {
        Button result = new Button();
        String label = resources.getString(property);
        result.setText(label);
        result.setOnAction(handler);
        return result;
    }

    // creates the display of the console
    private void displayConsole() {
        myRoot.getChildren().clear();
        myConsole = new Console(LANGUAGE, myCompiler);
        myRoot.setCenter(myWelcome.getPane());
        currentGridY = 0;
        currentGridX = 0;
    }


}
