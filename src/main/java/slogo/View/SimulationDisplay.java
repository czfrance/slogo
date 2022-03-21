package slogo.View;


import javafx.scene.control.*;
import javafx.scene.layout.*;
import slogo.SlogoView;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

/**
 * Creates all the necessary buttons we need in order to have our simulation running
 * @author Thivya Sivarajah
 */

public class  SimulationDisplay extends Region implements DashboardView {

    public static final int SLIDER_MINIMUM = 1;
    public static final int SLIDER_MAXIMUM = 10;
    public static final double SLIDER_START = 5;
    public static final String DEFAULT_RESOURCE_PACKAGE = "/slogo.languages/";

<<<<<<< HEAD
    Pane myPane;
     private BorderPane myRoot;
    // private StackPane myRoot;
=======
    private BorderPane myRoot;
>>>>>>> master
    private SketchbookView mySketch;
    private ResourceBundle myResources;
    private Button myPauseButton;
    private Button myPlayButton;
    private Button myResetButton;
    private VBox mySidePanel;
    private Label mySim;

    public SimulationDisplay(SketchbookView sketch) {

        myRoot = new BorderPane();
        mySketch = sketch;
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE+ "English");
        updateVariableDisplay(mySketch);
    }

    /**
     * Method used to update our sketch while the turtle runs as well (just to keep track of everything
     * It is important to note that this is our only public method in this class and it is part of our Dashboard
     * interface as well
     */
    @Override
<<<<<<< HEAD
    public BorderPane updateVariableDisplay(SketchbookView sketch) {

        // myRoot.setCenter(mySketch);
//        myRoot.setLeft(makeSidePanel());
//        myRoot.setBottom(makeConfigButtons());
        myRoot.setLeft(makeSidePanel());
        myRoot.setRight(makeConfigButtons());
=======
    public BorderPane updateVariableDisplay(SketchbookView sketch, BorderPane root) {
        FlowPane buttons = new FlowPane() ;
        buttons.getChildren().addAll(makeSidePanel());
        root.setCenter(buttons);
>>>>>>> master
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE+ "English");
        return myRoot;
    }

    private VBox makeSidePanel() {
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
        HBox resetNext = new HBox();
        resetNext.setId("resetNext");
        HBox play = new HBox();
        play.setId("PlayButton");
        HBox pause = new HBox();
        pause.setId("PauseButton");

        makePauseButton();
        makePlayButton();
        makeResetButton();

        play.getChildren().addAll(myPlayButton);
        pause.getChildren().addAll(myPauseButton);
        resetNext.getChildren().addAll(myResetButton);
        control.getChildren().addAll(play, pause, resetNext);
        return control;
    }

    private void makePauseButton() {
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
                }, myResources);
    }

    private void makePlayButton() {
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
    }

    private void makeResetButton() {
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
                }, myResources);
    }

}
