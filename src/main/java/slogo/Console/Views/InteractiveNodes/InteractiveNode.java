package slogo.Console.Views.InteractiveNodes;
/**
 * @Author: Prajwal
 * Abstract Class that creates ways to make nodes that display information about the current simulation
 * and allows the user to interact with it
 */

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

  /**
   *
   * @param title Name of the Instruciton/Variable
   * @param model Turtle Model in use
   * @param turtles currrent Turtle Collection
   * @param language application wide language currently inuse
   *
   * Constructor basically sets up a basic interactive square with the name of the variable/node in
   * question and then gives it a clicked on action
   */
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

  /**
   * implement in different ways to give information when hovered over
   */
  public abstract void hoverToolTip();

  /**
   * Implement to determine what occurs when the node is clicked on
   */
  public abstract void popup();

  /**
   * Gives values that have real time feedback a way to update themselves in different ways
   */
  public abstract void update();

  /**
   *
   * @return the javaFx node for a given variable or instruction
   */
  public Label getLabel(){
    return myLabel;
  }
}
