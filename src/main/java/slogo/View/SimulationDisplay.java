package slogo.View;

import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import slogo.SlogoView;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.ResourceBundle;



public class SimulationDisplay extends Region implements DashboardView {

    public static final int SLIDER_MINIMUM = 1;
    public static final int SLIDER_MAXIMUM = 10;
    public static final double SLIDER_START = 5;
    public static final String DEFAULT_RESOURCE_PACKAGE = "/slogo.languages/";

    Pane myPane;
     private BorderPane myRoot;
    // private StackPane myRoot;
    private SketchbookView mySketch;
    private SimulationDisplay mySimulation;
    private ResourceBundle myResources;
    private Button myPauseButton;
    private Button myPlayButton;
    private Button myResetButton;
    private VBox mySidePanel;
    private Label mySim;
    private String language;

    public SimulationDisplay(SketchbookView sketch) {
        // super(sketch.getPane());
        myRoot = new BorderPane();
//        myRoot = new StackPane();
        mySketch = sketch;
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE+ "English");
        updateVariableDisplay(mySketch);
    }


    @Override
    public BorderPane updateVariableDisplay(SketchbookView sketch) {

        // myRoot.setCenter(mySketch);
//        myRoot.setLeft(makeSidePanel());
//        myRoot.setBottom(makeConfigButtons());
        myRoot.setLeft(makeSidePanel());
        myRoot.setRight(makeConfigButtons());
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE+ "English");
        return myRoot;
    }

//    private void createScreen() {
//        language = "English";
//        createElements(language);
//        myPane.getChildren().add(mySidePanel);
//    }

    private void createElements(String val) {
        mySidePanel.getChildren().clear();
        // mySim = new Label(myResources.getString("Welcome"));
//        myWelcome.setId("welcome-text");
//        myContainer.setId("opening-window");
//        myWelcome.setTextAlignment(TextAlignment.CENTER);
//        myContainer.getChildren().addAll(myWelcome);
    }

    private VBox makeSidePanel() {
        mySidePanel = new VBox();
        mySidePanel.setId("sidePanel");
//        SideInfoPanel info = new SideInfoPanel(myRecord);
//        VBox infoBox = info.getPane();
//        bindWidth(infoBox, mySidePanel);
        VBox control = makeControlButtons();
        bindWidth(control, mySidePanel);
        VBox speed = makeSpeedSlider();
        mySidePanel.getChildren().addAll(control, speed);
        return mySidePanel;
    }

    private void bindWidth(Pane B, Pane A) {
        A.prefWidthProperty().bind(B.widthProperty());
    }

    private VBox makeSpeedSlider() {
        VBox speedBox = new VBox();
        speedBox.setId("speedBox");
        Slider slider = new Slider(SLIDER_MINIMUM, SLIDER_MAXIMUM, SLIDER_START);
        slider.valueProperty().addListener(
                (ov, old_val, new_val) -> mySketch.setAnimationSpeed(new_val));
        Label speed = new Label(myResources.getString("Speed"));
        speedBox.getChildren().addAll(slider, speed);
        return speedBox;
    }

    private Node makeConfigButtons() {
        HBox box = new HBox();
        box.setId("configBox");
        Button loadFile = SlogoView.makeButton("LoadFile", event -> {
            try {
                saveState();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }, myResources);
        Button saveFile = SlogoView.makeButton("SaveFile", event -> {
            try {
                saveState();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }, myResources);
//        ChoiceBox<String> gridShapeChoice = makeChoiceBox(GRID_SHAPE_OPTIONS,
//                (ov, old_val, new_val) -> changeGridShape(new_val));
//        gridShapeChoice.getSelectionModel().select(0);
        box.getChildren().addAll(loadFile, saveFile);
        return box;
    }

    public static ChoiceBox<String> makeChoiceBox(List<String> options,
                                                  ChangeListener<String> handler) {
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll(options);
        choiceBox.valueProperty().addListener(handler);
        return choiceBox;
    }

    private void saveState() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        mySketch.pause();
        // FileWriters save = new FileWriters(myResources, myRecord, myGridView.getCurrentGrid());
    }


    private VBox makeControlButtons() {
        VBox control = new VBox();
        control.setId("control");
        HBox playPause = new HBox();
        playPause.setId("playPause");
        HBox resetNext = new HBox();
        resetNext.setId("resetNext");

        myPauseButton = SlogoView.makeButton("PauseButton", event -> {
                    try {
                        mySketch.pause();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                },
                myResources);
        myPlayButton = SlogoView.makeButton("PlayButton", event -> mySketch.play(),
                myResources);
        myResetButton = SlogoView.makeButton("ResetButton", event -> mySketch.reset(),
                myResources);

        resetNext.getChildren().addAll(myResetButton);
        playPause.getChildren().addAll(myPauseButton, myPlayButton);
        control.getChildren().addAll(playPause, resetNext);
        return control;
    }

    public Pane getPane() {
        return myPane;
    }


}
