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

  public Console(String language, Compiler compiler){
    myStage = new Stage();
    ResourceBundle resources = ResourceBundle.getBundle(LANGUAGE_RESOURCE_PATH + language);
    myCompiler = compiler;
    myFileHandler = new FileHandler();
    myCmdHistory = new CommandHistory();
    generatePopup();
  }

  public Console(String language, TurtleModel turtleModel){
    this(language, new Compiler(language, turtleModel));
  }

  private void generatePopup(){
    BorderPane layout = new BorderPane();
    layout.setPadding(new Insets(10,10,10,10));
    myConsole = new TextArea();

    myConsole.setWrapText(true);
    myConsole.setEditable(true);
    myConsole.setPromptText("Write Code here");
    layout.setCenter(myConsole);
    Button chooseScript = new Button("Load Script");
    Button enter = new Button("Enter Code");

    chooseScript.setOnAction(e->{
      try {
        File script =myFileHandler.fileChoice(myStage);
        myCompiler.getCommands(myFileHandler.fileToString(script));
      }
      catch(NullPointerException nullPointerException){
        generateAlert("Error: No File Chosen");
      }
      catch(Exception err){
        generateAlert("File Error");
      }
    });
    enter.setOnAction(e->{
      if(!myConsole.getText().isEmpty()){
        try{
          myCompiler.getCommands(myConsole.getText());
          myConsole.clear();
        }catch(Exception err){
          generateAlert(err.getStackTrace().toString());
        }
      }
    });

    GridPane buttons = new GridPane();
    buttons.setHgap(230);
    buttons.add(chooseScript, 0,1);
    buttons.add(enter, 1,1);

    layout.setBottom(buttons);
    popup = new Scene(layout,400,400);
    myStage.setScene(popup);
    myStage.show();
  }

  private void generateAlert(String str){
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(str);
    alert.showAndWait();
  }
  public TextArea getConsole(){ return myConsole;}

}
