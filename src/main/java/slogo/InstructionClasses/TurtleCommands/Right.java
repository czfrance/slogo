package slogo.InstructionClasses.TurtleCommands;

import slogo.InstructionClasses.Instruction;

public class Right extends Instruction {
  public static final int RIGHT_PARAM_NUM = 1;

  public Right() {
    super(RIGHT_PARAM_NUM);
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
    return String.format("right %f\n", myDegrees);
  }
}
