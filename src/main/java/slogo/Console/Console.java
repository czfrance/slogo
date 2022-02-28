package slogo.Console;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import slogo.Compiler;
import slogo.Model.TurtleModel;

public class Console {


  private Compiler myCompiler;
  private Stage myStage;
  private ResourceBundle myResourceBundle;
  private static final String LANGUAGE_RESOURCE_PATH = "/slogo/languages/";
  private Button uploadButton;
  private Scene popup;
  private CommandHistory myCmdHistory;
  private TextArea myConsole;
  private FileHandler myFileHandler;

  public Console(String language, Compiler compiler) {
    myStage = new Stage();
    ResourceBundle resources = ResourceBundle.getBundle(LANGUAGE_RESOURCE_PATH + language);
    myCompiler = compiler;
    myFileHandler = new FileHandler();
    myCmdHistory = new CommandHistory();
    generatePopup();
  }

  public Console(String language, TurtleModel turtleModel) {
    this(language, new Compiler(language, turtleModel));
  }

  private void generatePopup() {
    BorderPane layout = new BorderPane();
    layout.setPadding(new Insets(10, 10, 10, 10));
    myConsole = new TextArea();

    myConsole.setWrapText(true);
    myConsole.setEditable(true);
    myConsole.setPromptText("Write Code here");
    layout.setCenter(myConsole);
    Button chooseScript = new Button("Load Script");
    Button enter = new Button("Enter Code");
    Button history = new Button("Command History");
    chooseScript.setOnAction(e -> {
      try {
        File script = myFileHandler.fileChoice(myStage);
      } catch (NullPointerException nullPointerException) {
        ConsoleAlerts alert = new ConsoleAlerts("Error: No File Chosen");
      } catch (Exception err) {
        ConsoleAlerts alert = new ConsoleAlerts("File Error");
      }
    });
    enter.setOnAction(e -> {
      if (!myConsole.getText().isEmpty()) {
        handleInput(myConsole.getText());
        myConsole.getText();
      }
    });
    history.setOnAction(e -> {
      myCmdHistory.showHistory();
    });

    HBox buttons = new HBox();
    buttons.setSpacing(50);
    buttons.getChildren().addAll(chooseScript, enter, history);

    layout.setBottom(buttons);
    BorderPane.setMargin(buttons, new Insets(10,10,0,0));
    popup = new Scene(layout, 400, 400);
    myStage.setScene(popup);
    myStage.show();
  }


  public TextArea getConsole() {
    return myConsole;
  }

  private void handleInput(String in) {
    try{
      myCompiler.getCommands(in);
      myCmdHistory.updateHistory(myConsole.getText());
    }
    catch(Exception e){
      ConsoleAlerts myAlert = new ConsoleAlerts("Error in the Instruction");
    }
  }


}
