package slogo.Console.Views;

import static slogo.Compiler.INSTRUCTION_PACKAGE;

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

  private void makeVBox() {
    for(String key:instructions.keySet()){
      InteractiveBasic node = new InteractiveBasic(key,myModel, myTurtles, myResources);
      layout.getChildren().add(node.getLabel());
    }
  }
  public void show(){
    myStage.show();
  }
}
