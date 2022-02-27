package slogo.InstructionClasses.MathOperations;

import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleModel;

public class Quotient extends MathOperation {
  public static final int QUOTIENT_PARAM_NUM = 2;

  public Quotient(TurtleModel turtleModel) {
    super(QUOTIENT_PARAM_NUM, turtleModel);
  }

  @Override
  public double returnValue() {
    double param1 = getMyParameters()[0].returnValue();
    double param2 = getMyParameters()[1].returnValue();
    return param1/param2;
  }

  @Override
  public String toString() {
    double param1 = getMyParameters()[0].returnValue();
    double param2 = getMyParameters()[1].returnValue();
    return String.format("quotient %f %f\n", param1, param2);
  }
}
