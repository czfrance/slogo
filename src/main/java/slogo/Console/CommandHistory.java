package slogo.Console;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CommandHistory {

  private Stage myStage;
  private ArrayList<String> history;
  private GridPane pastInstructions;
  private BorderPane root;
  private ScrollPane scrolling;
  private Scene myScene;
  private Insets insets = new Insets(20);
  public CommandHistory(){
    myStage = new Stage();
    history = new ArrayList<String>();
    setUpRoot();
    myScene = new Scene(root,400,400);
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
  private void setUpRoot(){
    pastInstructions = new GridPane();
    scrolling = new ScrollPane();
    scrolling.setContent(pastInstructions);
    Button save = new Button("Save");
    Button clear = new Button("Clear");
    clear.setOnAction(e->{
      history.clear();
      pastInstructions.getChildren().clear();
    });
    HBox buttons = new HBox();
    buttons.getChildren().addAll(save, clear);
    root = new BorderPane();
    root.setAlignment(scrolling, Pos.TOP_CENTER);
    root.setCenter(scrolling);
    root.setAlignment(buttons, Pos.BOTTOM_CENTER);
    root.setBottom(buttons);
    BorderPane.setMargin(scrolling, insets);
    BorderPane.setMargin(buttons, insets);
  }


}
