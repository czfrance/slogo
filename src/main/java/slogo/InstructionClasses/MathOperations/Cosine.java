package slogo.InstructionClasses.MathOperations;

import slogo.Model.TurtleCollection;

public class Cosine extends MathOperation {
  public static final int COSINE_PARAM_NUM = 1;

  public Cosine(TurtleCollection turtleModel) {
    super(COSINE_PARAM_NUM, turtleModel);
  }

  @Override
  public double returnValue() {
    double param1 = getMyParameters()[0].returnValue();
    return Math.cos(param1);
  }

}
