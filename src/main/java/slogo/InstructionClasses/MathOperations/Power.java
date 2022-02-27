package slogo.InstructionClasses.MathOperations;

import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleModel;

public class Power extends MathOperation {
  public static final int POWER_PARAM_NUM = 2;

  public Power(TurtleModel turtleModel) {
    super(POWER_PARAM_NUM, turtleModel);
  }

  @Override
  public double returnValue() {
    double param1 = getMyParameters()[0].returnValue();
    double param2 = getMyParameters()[1].returnValue();
    return Math.pow(param1, param2);
  }

  @Override
  public String toString() {
    double param1 = getMyParameters()[0].returnValue();
    double param2 = getMyParameters()[1].returnValue();
    return String.format("power %f %f\n", param1, param2);
  }
}
