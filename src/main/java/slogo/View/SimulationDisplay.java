package slogo.View;

import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import slogo.SlogoView;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

        myRoot = new BorderPane();
        mySketch = sketch;
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE+ "English");
        updateVariableDisplay(mySketch, myRoot);
    }


    @Override
    public BorderPane updateVariableDisplay(SketchbookView sketch, BorderPane root) {
        FlowPane buttons = new FlowPane() ;
//        buttons.getChildren().addAll(makeSidePanel(), makeConfigButtons());
        buttons.getChildren().addAll(makeSidePanel());
        root.setCenter(buttons);
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE+ "English");
        return root;
    }


    public VBox makeSidePanel() {
        mySidePanel = new VBox();
        mySidePanel.setId("sidePanel");
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

    public static ChoiceBox<String> makeChoiceBox(List<String> options,
                                                  ChangeListener<String> handler) {
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll(options);
        choiceBox.valueProperty().addListener(handler);
        return choiceBox;
    }

    private void saveState() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        mySketch.pause();
    }

    private void goState() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        mySketch.play();
    }

    private void clearState() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        mySketch.reset();
    }

    private VBox makeControlButtons() {
        VBox control = new VBox();
        control.setId("control");
//        HBox playPause = new HBox();
//        playPause.setId("playPause");

        HBox resetNext = new HBox();
        resetNext.setId("resetNext");
        HBox play = new HBox();
        play.setId("PlayButton");
        HBox pause = new HBox();
        pause.setId("PauseButton");

        myPauseButton = SlogoView.makeButton("PauseButton", event -> {
                    try {
                        saveState();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                },
                myResources);
        myPlayButton = SlogoView.makeButton("PlayButton", event -> {
            try {
                goState();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }, myResources);
        myResetButton = SlogoView.makeButton("ResetButton", event -> {
                    try {
                        clearState();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                },
                myResources);

        play.getChildren().addAll(myPlayButton);
        pause.getChildren().addAll(myPauseButton);
        resetNext.getChildren().addAll(myResetButton);
        control.getChildren().addAll(play, pause, resetNext);
        return control;
    }


}
