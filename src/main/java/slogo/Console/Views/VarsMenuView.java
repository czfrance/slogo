package slogo.Console.Views;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import slogo.Console.Views.InteractiveNodes.InteractiveVars;
import slogo.InstructionClasses.Variable;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleInsnModel;

public class VarsMenuView {
  private Stage myStage;
  private Map<String, Variable> myVars;
  private LinkedHashMap<String, InteractiveVars> allNodes;
  private ScrollPane myScrollPane;
  private VBox layout;
  private TurtleInsnModel myModel;
  private TurtleCollection myTurtles;
  private ResourceBundle myResources;

  public VarsMenuView(TurtleInsnModel model, TurtleCollection turtles, ResourceBundle bundle){
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
  }

  private void makeVBox() {
    layout.getChildren().removeAll();
    for (String key : allNodes.keySet()) {
      layout.getChildren().addAll(allNodes.get(key).getLabel());
    }
  }

  public void update(){
    if(myVars.equals(myModel.getCompiler().getVariableMap()))
      return;

    myVars = myModel.getCompiler().getVariableMap();

    if(allNodes.keySet().equals(myVars.keySet())){
      for(String key: allNodes.keySet()){
        allNodes.get(key).update(myVars.get(key));
      }
    }else{
      addDelete();
    }
  }

  private void addDelete(){
    for(String str: allNodes.keySet()){
      if(!myVars.containsKey(str))
        allNodes.remove(str);
    }
    for(String str: myVars.keySet()){
      if(!allNodes.containsKey(str)){
        allNodes.put(str, new InteractiveVars(str, myModel, myTurtles, myResources)))
      }
    }
    makeVBox();
  }
  public void show(){
    myStage.show();
  }
}
