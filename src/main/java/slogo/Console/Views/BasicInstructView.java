package slogo.Console.Views;
/**
 * @Author: Prajwal Jagadish
 * Arranges the Basic instructions into a easy-to-read menu that can be interated with for different possiblities
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import slogo.Console.Views.InteractiveNodes.InteractiveBasic;
import slogo.Console.Views.InteractiveNodes.InteractiveVars;
import slogo.InstructionClasses.Instruction;
import slogo.InstructionClasses.TurtleQueries.TurtleQuery;
import slogo.InstructionClasses.Variable;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleInsnModel;

public class BasicInstructView {

  private final ResourceBundle instructions = ResourceBundle.getBundle("slogo/languages/InstructionType");
  private Stage myStage;
  private ScrollPane myScrollPane;
  private VBox layout;
  private TurtleInsnModel myModel;
  private TurtleCollection myTurtles;
  private ResourceBundle myResources;

  /**
   * Sets up a vbox that acts as the menu and generates new nodes as needed until all the basic instructions are represented
   * @param model current TurtleInstruction model to get all the basic commands in question
   * @param turtles Current TurtleCollection
   * @param bundle Current resource bundle
   */
  public BasicInstructView(TurtleInsnModel model, TurtleCollection turtles, ResourceBundle bundle){
    myStage = new Stage();
    myModel = model;
    myTurtles = turtles;
    myResources = bundle;
    layout = new VBox();
    try{
      makeVBox();
    }catch (Exception e){
      e.printStackTrace();
    }
    ScrollPane myPane = new ScrollPane();
    myPane.setContent(layout);
    myPane.setFitToWidth(true);
    myPane.setPrefHeight(300);
    Scene myScene = new Scene(myPane);
    myStage.setScene(myScene);
  }

  /**
   * sets up the vbox where we interate through all the instructions until we have made a node for all of them
   */
  private void makeVBox() {
    for(String key:instructions.keySet()){
      InteractiveBasic node = new InteractiveBasic(key,myModel, myTurtles, myResources);
      layout.getChildren().add(node.getLabel());
    }
  }

  /**
   * shows the menu
   */
  public void show(){
    myStage.show();
  }
}
