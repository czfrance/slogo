package slogo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.CompilerExceptions.CompilerException;
import slogo.CompilerExceptions.NotAValueException;
import slogo.Model.InstructionModel;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleInsnModel;
import slogo.Model.TurtleModel;

class TurtleTest {

    TurtleInsnModel insnModel;
    TurtleCollection myTurtles;
    double x;
    double y;
    //assumption: facing right = 0 degrees, increases clockwise
    int heading;

    @BeforeEach
    void setup()
        throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        myTurtles = new TurtleCollection();
        insnModel = new TurtleInsnModel(myTurtles, "English");

        // Console input = new Console("", new Compiler(""));
        insnModel.addUserInput("forward 200");
        insnModel.addUserInput("back 200");
        insnModel.addUserInput("tell [ 1 2 3 ]");
        insnModel.addUserInput("forward 100");
        insnModel.addUserInput("tell [ 1 2 ]");
        insnModel.addUserInput("back 100");
    }

    @Test
    // check if we have the correct number of turtles
    void checkTotalTurles() {
        for (int i = 0; i < 6; i++) {
            insnModel.runNextInsn();
        }
        Map<Integer, TurtleModel> turtleMap = insnModel.getCreatedTurtleMap();
        Assertions.assertEquals(3, turtleMap.keySet().size());
    }

    @Test
    //check if run instruction returns the correct value
    void checkRunInsn() {
        Optional<Object> o = insnModel.runNextInsn();
        Assertions.assertTrue(o.isPresent());
        Assertions.assertEquals(200.0, o.get());
    }
}
