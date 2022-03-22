package slogo.Console.Views.InteractiveNodes;
/**
 * @Author: Prajwal
 * Implementation of interative node that allows users to interact with user defined variables
 */

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

  /**
   * Sets up the variable node
   * @param title name of the variable
   * @param model the current turtle model
   * @param turtles current turtle collection
   * @param myResources The current resourceBundle in use
   *
   */
  public InteractiveVars(String title, TurtleInsnModel model, TurtleCollection turtles, ResourceBundle myResources){
    super(title, model, turtles, myResources);
    myLanguage = myResources;
    myVar = (Variable) super.myModel.getCompiler().getVariableMap().get(title);
    hoverToolTip();
  }

  @Override
  /**
   * Implementation of the abstract hoverToolTip
   * Displays the variable value when hovered over
   */
  public void hoverToolTip() {
    super.myLabel.setTooltip(new Tooltip(myVar.valToString()));
    super.myLabel.getTooltip().setShowDelay(Duration.seconds(0.5));
  }

  @Override
  /**
   * Implementation of the abstract popup which generates a popup that allows users to change the variable value
   */
  public void popup() {
    TextInputDialog dialog = new TextInputDialog("Type in New Value");
    dialog.showAndWait();
    try{
      myVar.setVariable(Double.parseDouble(dialog.getEditor().getText()));
    }catch(Exception e){}
    update();
  }

  /**
   * Changes the tooltip message to keep it upto date
   */
  @Override
  public void update() {
    hoverToolTip();
  }
  /**
   * Changes the tooltip message to keep it upto date
   */
  public void update(Variable newVar){
    myVar = newVar;
    hoverToolTip();
  }
}
