package slogo.Console.Views.InteractiveNodes;

import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import slogo.InstructionClasses.Variable;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleInsnModel;

public class InteractiveVars extends InteractiveNode {

  private DoubleProperty doubleProperty;
  private Variable myVar;
  public InteractiveVars(String title, TurtleInsnModel model, TurtleCollection turtles, ResourceBundle myResources){
    super(title, model, turtles, myResources);
    myVar = (Variable) super.myModel.getCompiler().getVariableMap().get(title);
    hoverToolTip();
  }

  @Override
  public void hoverToolTip() {
    Tooltip tip = new Tooltip(myVar.valToString());
    super.myLabel.setTooltip(tip);
  }

  @Override
  public void popup() {
    TextInputDialog dialog = new TextInputDialog(super.myResources.getString("Type in New Value"));
    dialog.showAndWait();
    if(!(dialog.getEditor().getText().isEmpty()) && dialog.getEditor().getText() != null)
      myVar.setVariable(Double.parseDouble(dialog.getEditor().getText()));
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
