package slogo.View;

import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import slogo.Model.TurtleModel;
import slogo.SlogoView;
import slogo.View.DashboardView;
import slogo.View.Pen.LinePen;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.ResourceBundle;



public class SimulationDisplay implements DashboardView {

    public static final int SLIDER_MINIMUM = 1;
    public static final int SLIDER_MAXIMUM = 10;
    public static final double SLIDER_START = 5;

    private BorderPane myRoot;
    private SketchbookView mySketch;
    private ResourceBundle myResources;
    private Button myPauseButton;
    private Button myPlayButton;
    private Button myResetButton;
    private VBox mySidePanel;

    public SimulationDisplay(SketchbookView sketch, ResourceBundle resources) {
        myRoot = new BorderPane();
        mySketch = sketch;
        myResources = resources;
        myRoot.setCenter(mySketch);
        myRoot.setLeft(makeSidePanel());
        myRoot.setBottom(makeConfigButtons());
    }
    @Override
    public void updateVariableDisplay() {

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


}
