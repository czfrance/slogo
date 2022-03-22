package slogo.Console.Views;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import slogo.Console.Views.InteractiveNodes.InteractiveVars;
import slogo.InstructionClasses.Variable;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleInsnModel;

/**
 * @author Prajwal Jagadish
 * Creates a Menu where users can view the variables that they have defined and then change them as necessary
 */
public class VarsMenuView {
  private Stage myStage;
  private Map<String, Variable> myVars;
  private LinkedHashMap<String, InteractiveVars> allNodes;
  private ScrollPane myScrollPane;
  private VBox layout;
  private TurtleInsnModel myModel;
  private TurtleCollection myTurtles;
  private ResourceBundle myResources;


  /**
   * Sets up a scroll pane so that the user can look through the entire variable lsit
   * @param model - Current Instruction model
   * @param turtles - Current Turtle Colleciton
   * @param bundle - Languagge Bundle in use
   */
  public VarsMenuView(TurtleInsnModel model, TurtleCollection turtles, ResourceBundle bundle){
    myStage = new Stage();
    myModel = model;
    myTurtles = turtles;
    myResources = bundle;
    myVars = model.getCompiler().getVariableMap();
    allNodes = new LinkedHashMap<>();
    layout = new VBox();
    makeVBox();
    ScrollPane myPane = new ScrollPane();
    myPane.setContent(layout);
    myPane.setFitToWidth(true);
    myPane.setPrefHeight(300);

    Scene myScene = new Scene(myPane);
    myStage.setScene(myScene);

  }

  /**
   * Iterates through the entire list of variables and adds the respective nodes to the vbox
   */
  private void makeVBox() {
    layout.getChildren().removeAll();
    for (String key : allNodes.keySet()) {
      layout.getChildren().addAll(allNodes.get(key).getLabel());
    }
  }

  /**
   * called whenever a change is made to the variable hashmap
   * If ther is the same number of nodes and variable it will go through and update all the nodes
   * Otherwise it will call addDelete() to add or delete any nodes
   */
  public void update(){
    if(allNodes.keySet().equals(myVars.keySet())){

      for(String key: allNodes.keySet()){
        allNodes.get(key).update(myVars.get(key));
      }
    }else{

      addDelete();
    }
  }

  /**
   * Adds or deletes nodes that ar no math between the variable hashmap in the compiler and the node map in the menu
   * Adds if not found node hashmap
   * Delete if not found in variable map
   */
  private void addDelete(){

    for(String str: allNodes.keySet()){
      if(!myVars.containsKey(str))
        allNodes.remove(str);
    }
    for(String str: myVars.keySet()){
      if(!allNodes.containsKey(str)){
        allNodes.put(str, new InteractiveVars(str, myModel, myTurtles, myResources));
      }
    }
    makeVBox();
  }
  public void show(){
    myStage.show();
  }
}
