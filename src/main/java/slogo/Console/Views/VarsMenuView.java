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

  private void makeVBox() {
    layout.getChildren().removeAll();
    for (String key : allNodes.keySet()) {
      layout.getChildren().addAll(allNodes.get(key).getLabel());
    }
  }

  public void update(){
    if(allNodes.keySet().equals(myVars.keySet())){
      System.out.println("this occurs line 56");
      for(String key: allNodes.keySet()){
        allNodes.get(key).update(myVars.get(key));
      }
    }else{
      System.out.println("this occurs line 61");
      addDelete();
    }
  }

  private void addDelete(){
    System.out.println("this occurs line 67");
    for(String str: allNodes.keySet()){
      if(!myVars.containsKey(str))
        allNodes.remove(str);
    }
    for(String str: myVars.keySet()){
      if(!allNodes.containsKey(str)){
        System.out.println("this occurs line 74");
        allNodes.put(str, new InteractiveVars(str, myModel, myTurtles, myResources));
      }
    }
    makeVBox();
  }
  public void show(){
    myStage.show();
  }
}
