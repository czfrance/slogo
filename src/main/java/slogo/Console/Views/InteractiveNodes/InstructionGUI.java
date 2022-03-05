package slogo.Console.Views.InteractiveNodes;

import javafx.scene.control.Slider;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleInsnModel;

public class InstructionGUI {

  private String myName;
  private Instruction myInstruction;
  private TurtleInsnModel myModel;
  private TurtleCollection myTurtles;
  private int numOfParameters;
  public InstructionGUI(String title, Instruction myInstruction, TurtleInsnModel myModel, TurtleCollection myTurtles) {
    this.myName = title;
    this.myInstruction = myInstruction;
    this.myModel = myModel;
    this.myTurtles = myTurtles;
    numOfParameters = this.myInstruction.getNumParameters();
    makeGui();
  }

  private void makeGui() {

  }

  private Slider makeSlider(){
    Slider slider = new Slider();
    slider.setMin(0);
    slider.setMax(100);
    slider.setValue(40);
    slider.setShowTickLabels(true);
    slider.setShowTickMarks(true);
    slider.setMajorTickUnit(50);
    slider.setMinorTickCount(5);
    slider.setBlockIncrement(10);
    return slider;
  }
}
