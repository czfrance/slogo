package slogo.InstructionClasses.MathOperations;

import slogo.Model.TurtleCollection;

public class Tangent extends MathOperation {
  public static final int TAN_PARAM_NUM = 1;

  public Tangent(TurtleCollection turtleModel) {
    super(TAN_PARAM_NUM, turtleModel);
  }

  @Override
  public double returnValue() {
    double param1 = getMyParameters()[0].returnValue();
    return Math.tan(param1);
  }
}
