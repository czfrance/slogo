package slogo.InstructionClasses.MathOperations;

import slogo.Model.TurtleCollection;

public class ArcTangent extends MathOperation{
  public static final int ARCTAN_PARAM_NUM = 1;

  public ArcTangent(TurtleCollection turtleModel) {
    super(ARCTAN_PARAM_NUM, turtleModel);
  }

  @Override
  public double returnValue() {
    double param1 = getMyParameters()[0].returnValue();
    return Math.atan(param1);
  }
}
