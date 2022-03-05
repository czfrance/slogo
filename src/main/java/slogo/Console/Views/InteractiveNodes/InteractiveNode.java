package slogo.Console.Views.InteractiveNodes;

import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleInsnModel;

public abstract class InteractiveNode {

  protected Label myLabel;
  protected String name;
  private final int WIDTH = 85;
  private final int HEIGHT = 50;
  protected TurtleInsnModel myModel;
  protected TurtleCollection myTurtles;
  protected ResourceBundle myResources;
  public InteractiveNode(String title, TurtleInsnModel model, TurtleCollection turtles, ResourceBundle language){
    name = title;
    myModel = model;
    myTurtles = turtles;
    myLabel = new Label(title);
    myLabel.setTextAlignment(TextAlignment.CENTER);
    myLabel.setPrefSize(WIDTH, HEIGHT);
    myLabel.setStyle("-fx-background: rgb(225, 228, 203); -fx-border-color: black");
    myLabel.setOnMouseClicked(e->popup());
  }

  public abstract void hoverToolTip();

  public abstract void popup();
  public abstract void update();
  public Label getLabel(){
    return myLabel;
  }
}
