package slogo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import slogo.Model.TurtleModel;

class TurtleTest {

    InstructionModel insnModel;
    double x;
    double y;
    //assumption: facing right = 0 degrees, increases clockwise
    int heading;

    @BeforeEach
    void setup() {
        insnModel = new InstructionModel();
        x = 0;
        y = 0;
        heading = 90;
        TurtleModel model = new TurtleModel(0, 0, 90);
        // Console input = new Console("", new Compiler(""));
        insnModel.addInsn("forward 200");
        insnModel.addInsn("back 200");
        insnModel.addInsn("forward 200");
        insnModel.addInsn("back 200");
    }

    @Test
    // see if we have all of our instructions
    // CHECK IN CODE
    void checkNumberOfInstruction() {
        String[] insn = insnModel.getNextInsn().split(" ");
        Assertions.assertEquals(4, insn.length);
    }

    @Test
    // negative test to see if our instructions exist and are being added properly
    void checkNoInstructions() {
        Assertions.assertTrue(!insnModel.hasNextInsn());
    }

    @Test
    void checkInts() {
        String[] insn = insnModel.getNextInsn().split(" ");
        int[] params = new int[insn.length-1];
        for (int i = 1; i < insn.length; i++) {
            params[i-1] = Integer.parseInt(insn[i]);
        }
        Assertions.assertEquals(params[0], 200);
    }
}
