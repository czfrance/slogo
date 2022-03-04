package slogo.InstructionClasses.MathOperations;

import slogo.Model.TurtleCollection;

public class Sine extends MathOperation{

  public static final int SINE_PARAM_NUM = 1;

  public Sine(TurtleCollection turtleModel) {
    super(SINE_PARAM_NUM, turtleModel);
  }

  @Override
  public double returnValue() {
    double param1 = getMyParameters()[0].returnValue();
    return Math.sin(param1);
  }
}
