package slogo.Console.Views.InteractiveNodes;

import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import slogo.InstructionClasses.Variable;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleInsnModel;

public class InteractiveVars extends InteractiveNode {

  private DoubleProperty doubleProperty;
  private ResourceBundle myLanguage;
  private Variable myVar;
  public InteractiveVars(String title, TurtleInsnModel model, TurtleCollection turtles, ResourceBundle myResources){
    super(title, model, turtles, myResources);
    myLanguage = myResources;
    myVar = (Variable) super.myModel.getCompiler().getVariableMap().get(title);
    hoverToolTip();
  }

  @Override
  public void hoverToolTip() {
    super.myLabel.setTooltip(new Tooltip(myVar.valToString()));
    super.myLabel.getTooltip().setShowDelay(Duration.seconds(0.5));
  }

  @Override
  public void popup() {
    TextInputDialog dialog = new TextInputDialog("Type in New Value");
    dialog.showAndWait();
    try{
      myVar.setVariable(Double.parseDouble(dialog.getEditor().getText()));
    }catch(Exception e){}
    update();
  }

  @Override
  public void update() {
    hoverToolTip();
  }

  public void update(Variable newVar){
    myVar = newVar;
    hoverToolTip();
  }
}
