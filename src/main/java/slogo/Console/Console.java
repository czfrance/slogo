package slogo.Console;
import java.io.File;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import slogo.Compiler;
import slogo.Console.Views.BasicInstructView;
import slogo.Console.Views.VarsMenuView;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleInsnModel;
import slogo.SlogoView;

public class Console {


  private TurtleInsnModel myInstructionModel;
  private Stage myStage;
  private ResourceBundle myResourceBundle;
  private static final String LANGUAGE_RESOURCE_PATH = "/slogo/languages/";
  private Button uploadButton;
  private Scene popup;
  private CommandHistory myCmdHistory;
  private TextArea myConsole;
  private FileOpener myFileOpener;
  private TurtleCollection myTurtleCollection;
  private VarsMenuView myVarsMenu;

  public Console(ResourceBundle language, TurtleCollection turtles, TurtleInsnModel model) {
    myStage = new Stage();
    myTurtleCollection = turtles;
    ResourceBundle myResourceBundle = language;
    myInstructionModel = model;
    myFileOpener = new FileOpener(language);
    myCmdHistory = new CommandHistory(myResourceBundle);
    generatePopup();
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
    MenuButton interactives = generateInteractives();
    chooseScript.setOnAction(e -> {
      try {
        File script = myFileOpener.fileChoice(myStage);
        handleInput(myFileOpener.fileToString(script));
      } catch (NullPointerException nullPointerException) {
        ConsoleAlerts alert = new ConsoleAlerts("Error: No File Chosen");
      } catch (Exception err) {
        ConsoleAlerts alert = new ConsoleAlerts("File Error");
      }
    });

    enter.setOnAction(e -> {
      if (!myConsole.getText().isEmpty()) {
        handleInput(myConsole.getText());
        myConsole.clear();
      }
    });
    history.setOnAction(e -> {
      myCmdHistory.showHistory();
    });

    HBox buttons = new HBox();
    buttons.setSpacing(50);
    buttons.getChildren().addAll(chooseScript, enter, history, interactives);

    layout.setBottom(buttons);
    BorderPane.setMargin(buttons, new Insets(10,10,0,0));
    popup = new Scene(layout, 400, 600);
    myStage.setScene(popup);
    myStage.show();
  }


  public TextArea getConsole() {
    return myConsole;
  }

  private void handleInput(String in) {
    System.out.println(in);
    try{
      myInstructionModel.addUserInput(in);
      myCmdHistory.updateHistory(in);
      myVarsMenu.update();
      // SlogoView.displaySketch();
    }
    catch(Exception e){
      ConsoleAlerts myAlert = new ConsoleAlerts("Error in the Instruction");
      // SlogoView.displaySketch();
    }
  }

  private MenuButton generateInteractives() {
    myVarsMenu = new VarsMenuView(myInstructionModel, myTurtleCollection, myResourceBundle);
    BasicInstructView basicMenu = new BasicInstructView(myInstructionModel, myTurtleCollection, myResourceBundle);
    MenuItem varsButton = new MenuItem("Your Variables");
    MenuItem basicButton = new MenuItem("Basic Commmands");

    varsButton.setOnAction(e->myVarsMenu.show());
    basicButton.setOnAction(e->basicMenu.show());

    MenuButton menuChoice = new MenuButton("Interactives");
    menuChoice.getItems().addAll(varsButton, basicButton);
    return menuChoice;
  }
}
