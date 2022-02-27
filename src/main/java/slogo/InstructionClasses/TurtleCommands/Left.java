package slogo.InstructionClasses.TurtleCommands;

import slogo.InstructionClasses.Instruction;

public class Left extends Instruction {
  public static final int LEFT_PARAM_NUM = 1;

  public Left() {
    super(LEFT_PARAM_NUM);
  }

  @Override
  public double returnValue() {
    double myDegrees = getMyParameters()[0].returnValue();
    return myDegrees;
  }

  @Override
  public void run() {

  }

  @Override
  public String toString() {
    double myDegrees = getMyParameters()[0].returnValue();
    return String.format("left %f\n", myDegrees);
  }
}
