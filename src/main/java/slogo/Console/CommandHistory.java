package slogo.Console;

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CommandHistory {

  private Stage myStage;
  private ArrayList<String> history;
  private GridPane pastInstructions;
  private ScrollPane scrolling;
  private Scene myScene;
  public CommandHistory(){
    myStage = new Stage();
    history = new ArrayList<String>();
    pastInstructions = new GridPane();
    scrolling = new ScrollPane();
    scrolling.setContent(pastInstructions);
    myScene = new Scene(scrolling,400,400);
    myStage.setScene(myScene);
  }

  public void updateHistory(String instructions){
    history.add(instructions);
    constructPane();
  }

  private void constructPane() {
    Text text = new Text(history.get(history.size()-1));
    pastInstructions.add(text, 0,history.size()-1);
  }

  public void showHistory(){
    myStage.showAndWait();
  }

}
