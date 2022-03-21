package slogo.InstructionClasses.MathOperations;

import slogo.Model.TurtleCollection;

public class Remainder extends MathOperation{
  public static final int REMAINDER_PARAM_NUM = 2;

  public Remainder(TurtleCollection turtleModel) {
    super(REMAINDER_PARAM_NUM, turtleModel);
  }

  @Override
  public double returnValue() {
    double param1 = getMyParameters()[0].returnValue();
    double param2 = getMyParameters()[1].returnValue();
    return param1 % param2;
  }
}
