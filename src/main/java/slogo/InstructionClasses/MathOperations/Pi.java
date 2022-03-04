package slogo.InstructionClasses.MathOperations;

import slogo.Model.TurtleCollection;

public class Pi extends MathOperation {
  public static final int PI_PARAM_NUM = 0;

  public Pi(TurtleCollection turtleModel) {
    super(PI_PARAM_NUM, turtleModel);
  }

  @Override
  public double returnValue() {
    return Math.PI;
  }
}
