package slogo.InstructionClasses.MathOperations;

import slogo.Model.TurtleCollection;

public class Minus extends MathOperation {
  public static final int MINUS_PARAM_NUM = 1;

  public Minus(TurtleCollection turtleModel) {
    super(MINUS_PARAM_NUM, turtleModel);
  }

  @Override
  public double returnValue() {
    double param1 = getMyParameters()[0].returnValue();
    return -param1;
  }
}
