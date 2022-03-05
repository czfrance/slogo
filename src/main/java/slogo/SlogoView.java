package slogo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import slogo.Console.Console;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleInsnModel;
import slogo.Model.TurtleModel;
import slogo.View.OpeningWindow;


import slogo.View.SimulationDisplay;
import slogo.View.SketchbookView;

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
    private static BorderPane myRoot;

    private OpeningWindow myWelcome;
    private Console myConsole;
    private Compiler myCompiler;
    private static SketchbookView mySketch;
    private HBox TitleBox;
    private ScrollPane ScrollBox;
    private HBox ScreenConfigBox;
    private Label titleText;
    private static SimulationDisplay mySimulation;
    private static GridPane gridOfSimulations;

    private static int currentGridY;
    private static int currentGridX;
    private boolean inNightMode;
    private static String myLanguage;

    private TurtleCollection myTurtleCollection;
    private static TurtleModel myTurtleModel; // this is a temp solution
    private TurtleInsnModel myModel;

    /**
     * Initializes the starting properties need to create the initial simulation
     *
     * @param language - chooses what language file to read from between: English
     */
    public SlogoView(String language) {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
        myLanguage = language;
        currentGridY = 0;
        currentGridX = 0;
        myRoot = new BorderPane();
        STYLESHEET = "welcome.css";
        inNightMode = false;

        //temp solution delete later
        myTurtleCollection = new TurtleCollection();
        //myTurtleModel = new TurtleModel(0,0,0);
        myTurtleModel = myTurtleCollection.getActiveTurtle();
        myModel = new TurtleInsnModel(myTurtleCollection, language);
    }

        /**
         * Creates scene to hold all of the elements to be displayed to the user.
         *
         * @param width  - width in dixels of initial window
         * @param height - height in pixels of initial window
         * @return scene holding all elements displayed
         */
    public Scene makeScene(int width, int height, Stage stage) {
        Scene scene = new Scene(myRoot, width, height);
        displayWelcome(scene, stage);
        // displaySketch(stage);
        return scene;
    }

    //Creates the initial welcome screen for the user to select a language
    private void displayWelcome(Scene scene, Stage myStage) {
        scene.getStylesheets()
                .add(getClass().getResource("/welcome.css").toExternalForm());
        myRoot.getChildren().clear();
        myWelcome = new OpeningWindow(myResources);
        myRoot.setCenter(myWelcome.getPane());
        Button proceed = SlogoView.makeButton("Go", event -> {
                    try {
                        displaySketch(myStage, scene);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                },
                myResources);
        Button compiler = SlogoView.makeButton("Compiler", event -> displayConsole(), myResources);
        myWelcome.getContainer().getChildren().addAll(proceed, compiler);
        myWelcome.getContainer().setAlignment(Pos.CENTER);
        currentGridY = 0;
        currentGridX = 0;
        myStage.setScene(scene);
        myStage.show();
    }

    public void displaySketch(Stage stage, Scene scene) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        myRoot.getChildren().clear();
        scene.getStylesheets()
                .add(getClass().getResource("/simdisplay.css").toExternalForm());
        myTurtleModel = new TurtleModel(0, 0, 90);
        //urtleCollection collection = new TurtleCollection();
        //TurtleInsnModel insnModel = new TurtleInsnModel(collection, myLanguage);

        // mySketch = new SketchbookView(myTurtleModel);
        mySketch = new SketchbookView(myModel);
        mySimulation = new SimulationDisplay(mySketch);
        myRoot = mySimulation.updateVariableDisplay(mySketch, myRoot);
        myRoot.getChildren().add(mySketch.makeScene());
        stage.show();
        mySketch.play();
    }

    private static void setupTurtleViews() {
        gridOfSimulations = new GridPane();
        gridOfSimulations.prefWidthProperty().bind(myRoot.widthProperty());
        gridOfSimulations.prefHeightProperty().bind(myRoot.heightProperty());
    }

    private Node makeSimulationConfigRow() {
        ScreenConfigBox = new HBox();
        Button addSim = makeButton("AddSimulation", event -> addSimulation(), myResources);
        Button removeSim = makeButton("RemoveSimulation", event -> removeSimulation(), myResources);
        ScreenConfigBox.setId("configBox");
        ScreenConfigBox.getChildren().addAll(addSim, removeSim);
        return ScreenConfigBox;

    }

    //removes the bottom-most right-most simulation that is currently being displayed
    private void removeSimulation() {
        if (!(currentGridY == 0 && currentGridX == 1)) {
            if (currentGridX == 1) {
                currentGridX--;
            } else {
                currentGridY--;
                currentGridX++;
            }
            gridOfSimulations.getChildren().remove(currentGridY * 2 + currentGridX);
        }
    }

    private void addSimulation() {
//        SketchbookView sketch = new SketchbookView(myTurtleModel);
        SketchbookView sketch = new SketchbookView(myModel);
        gridOfSimulations.add(sketch.getBorderPane(), currentGridX, currentGridY);
        if (currentGridX == 1) {
            currentGridY++;
            currentGridX = 0;
        } else {
            currentGridX++;
        }
    }

//    private static void setupSketch(TurtleModel turtle) {
//        mySketch = new SketchbookView(turtle);
//        // mySimulation = new SimulationDisplay(mySketch);
////        mySketch.play();
////        mySketch.prefWidthProperty().bind(myRoot.widthProperty());
////        mySketch.prefHeightProperty().bind(myRoot.heightProperty());
//    }

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
        myConsole = new Console(myResources, myTurtleCollection,  myModel);
        myRoot.setCenter(myWelcome.getPane());
        currentGridY = 0;
        currentGridX = 0;
    }

    //creates a popup message with the given passed message as a parameter
    public static void showMessage(AlertType type, String message) {
        (new Alert(type, message, new ButtonType[0])).showAndWait();
    }


}
