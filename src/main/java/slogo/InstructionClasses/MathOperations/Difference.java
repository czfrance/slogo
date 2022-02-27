package slogo.InstructionClasses.MathOperations;

import slogo.InstructionClasses.Instruction;

public class Difference extends Instruction {
  public static final int DIFF_PARAM_NUM = 2;

  public Difference() {
    super(DIFF_PARAM_NUM);
  }
  @Override
  public double returnValue() {
    double param1 = getMyParameters()[0].returnValue();
    double param2 = getMyParameters()[1].returnValue();
    return param1 - param2;
  }

  @Override
  public void run() {

  }

  @Override
  public String toString() {
    double param1 = getMyParameters()[0].returnValue();
    double param2 = getMyParameters()[1].returnValue();
    return String.format("difference %f %f\n", param1, param2);
  }
}
