package slogo.Console;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.CompilerPackage.Compiler;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleModel;

class ConsoleTest {

    public static final String DEFAULT_LANGUAGE = "English";
    private ResourceBundle myErrorBundle;
    private Compiler myCompiler;
    Stage myStage;
    TurtleModel myModel;

    @BeforeEach
    void setup() {
        myModel = new TurtleModel(0,0,0);
        TurtleCollection myCollection = new TurtleCollection();
        myCompiler = new Compiler(DEFAULT_LANGUAGE, myCollection);
        myErrorBundle = ResourceBundle.getBundle(Compiler.ERROR_RESOURCE_PACKAGE+DEFAULT_LANGUAGE);
    }

    @Test
    void checkFileChooser() {
        FileChooser result = new FileChooser();
        result.setTitle("Open Data File");
        // pick a reasonable place to start searching for files
        result.setInitialDirectory(new File(System.getProperty("user.dir")));
        result.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("Text Files", ".txt"));

    }


}
