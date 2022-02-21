package slogo;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ObservableDoubleValue;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import slogo.Model.TurtleModel;
import slogo.View.SketchbookView;


/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main extends Application {
    private static final String LANGUAGE_RESOURCE_PATH = "/slogo/languages/";
    private static final String EXAMPLE_PROGRAMS_PATH = "/examples";
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

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


    @Override
    public void start(Stage stage) {
        TurtleModel model = new TurtleModel(0, 0, 90);
//        Console input = new Console("", new Compiler(""));
      model.addInsn("forward 100");
      model.addInsn("back 100");
      model.addInsn("right 45");
      model.addInsn("left 90");
      model.addInsn("setHeading 270");
      model.addInsn("towards -1 0");
      model.addInsn("setXY -1 0");

      SketchbookView view = new SketchbookView(model);
      stage.setScene(view.makeScene());
      stage.show();

      view.play();
      //model.addInsn("forward 50");
    }

    /**
     * Start of the program.
     */
//    public static void main (String[] args) {
//        Main m = new Main();
//        System.out.println(m.getVersion());
//        System.out.println(m.getCommand("English", "Forward"));
//        System.out.println(m.getExampleProgram("loops", "star"));
//
//        TurtleModel model = new TurtleModel(0, 0, 90);
//        model.addInsn("forward 50");
//        try {
//            model.runNextInsn();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        System.out.printf("location: %f, %f\n", model.getNextPos()[0], model.getNextPos()[1]);
//        SketchbookView view = new SketchbookView(model);
//        view.makeScene();
//
//    }
}
