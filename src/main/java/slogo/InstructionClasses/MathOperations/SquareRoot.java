package slogo.InstructionClasses.MathOperations;

import slogo.Model.TurtleCollection;

public class SquareRoot extends MathOperation {
  public static final int SQRT_PARAM_NUM = 1;

  public SquareRoot(TurtleCollection turtleModel) {
    super(SQRT_PARAM_NUM, turtleModel);
  }

  @Override
  public double returnValue() {
    double param1 = getMyParameters()[0].returnValue();
    return Math.sqrt(param1);
  }
}
