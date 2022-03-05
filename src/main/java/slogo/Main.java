package slogo;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import slogo.Console.LanguageMenu;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleInsnModel;
import slogo.Model.TurtleModel;
import slogo.Console.Console;
import slogo.View.SimulationDisplay;
import slogo.View.SketchbookView;


/**
 * Purpose: The main class which the program is run from. Contains the bare necessary static
 * final variables for the start method in taking and creating a CellSocietyView object to display
 *
 * Assumptions: Wouldn't do much besides start up the simulation and display it
 *
 *
 * Example: When we run the program, it should start up the simulation viewer.
 *
 * Other: N/A
 *
 * @author Brandon Bae, Cynthia France, Prajwal Jagdish, & Thivya Sivarajah
 */
public class Main extends Application {
    // useful names for constant values used, ADD TO RESOURCE FILE (note to self from Thivya)
    public static final String TITLE = "Cell Society";
    public static final String LANGUAGE = "English";
    public static final Dimension DEFAULT_SIZE = new Dimension(800, 600);
    private static final String LANGUAGE_RESOURCE_PATH = "/slogo/languages/";
    private static final String EXAMPLE_PROGRAMS_PATH = "/examples";
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final int[] INITIAL_XY = new int[]{0, 0};
    public static final int INITIAL_HEADING = 90;
    private ResourceBundle myResources;
    private javafx.scene.control.Button myPauseButton;
    private javafx.scene.control.Button myPlayButton;
    private Button myResetButton;
    private SketchbookView sketch;
    private BorderPane theFeatures;
    public static final String DEFAULT_RESOURCE_PACKAGE = "/slogo.languages/";

    // the next three methods should be used somewhere else if we are to make an efficient use of design
    /**
     * Get command in a given language.
     */
    public String getCommand (String language, String command) {
        ResourceBundle resources = ResourceBundle.getBundle(LANGUAGE_RESOURCE_PATH + language);
        return resources.getString(command);
    }

    /**
     * Get first line of given example program.
     */
    public String getExampleProgram (String category, String example) {
        // regular expression representing one or more whitespace characters (space, tab, or newline)
        final String NEWLINE = "\\n+";

        List<String> lines = readFile(String.format("%s/%s/%s.slogo", EXAMPLE_PROGRAMS_PATH, category, example), NEWLINE);
        return lines.get(0).trim();
    }

    /**
     * A method to test (and a joke :).
     */
    public double getVersion () {
        return 0.001;
    }

    // this code is dense, hard to read, and throws exceptions so better to wrap in method
    private List<String> readFile (String filename, String delimiter) {
        try {
            String path = getClass().getResource(filename).toExternalForm();
            String data = new String(Files.readAllBytes(Paths.get(new URI(path))));
            return Arrays.asList(data.split(delimiter));
        }
        catch (URISyntaxException | IOException | NullPointerException e) {
            // NOT ideal way to handle exception, but this is just a simple test program
            System.out.println("ERROR: Unable to read input file " + e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Start of the program.
     */

    @Override
    public void start(Stage stage)
        throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
      LanguageMenu menu = new LanguageMenu();
      //TurtleModel model = new TurtleModel(0, 0, 90);
      TurtleCollection collection = new TurtleCollection();
      TurtleInsnModel insnModel = new TurtleInsnModel(collection, menu.getAppLanguage());
      Console console = new Console(menu.getLanguageBundle(), collection, insnModel);
      //Console console = new Console("English",model);

      insnModel.addUserInput("tell [ 1 2 ]");
      insnModel.addUserInput("turtles");
      insnModel.addUserInput("forward 50");
      insnModel.addUserInput("rt 90");
      insnModel.addUserInput("forward 50");
      insnModel.addUserInput("tell [ 1 ]");
      insnModel.addUserInput("lt 90 forward 50");


      //insnModel.addUserInput("setXY -100 0");

//      SketchbookView view = new SketchbookView(model);
//      sketch = new SketchbookView(insnModel);
//      SimulationDisplay mySimulation = new SimulationDisplay(sketch);
//      theFeatures = new BorderPane();
//      theFeatures = mySimulation.updateVariableDisplay(sketch, theFeatures);
//      // theFeatures.getChildren().addAll(mySimulation.makeSidePanel(), mySimulation.makeConfigButtons());
////      theFeatures.setBottom(mySimulation.makeConfigButtons());
//
//      stage.setScene(sketch.makeScene(theFeatures));
//      stage.show();
//      sketch.play();

      SketchbookView sketch = new SketchbookView(insnModel);
      //SimulationDisplay mySimulation = new SimulationDisplay(sketch);
      //theFeatures = mySimulation.updateVariableDisplay(sketch, theFeatures);

      stage.setScene(sketch.makeScene());
      //stage.setScene(sketch.makeScene(theFeatures));
      stage.show();
      sketch.play();

    }
}
