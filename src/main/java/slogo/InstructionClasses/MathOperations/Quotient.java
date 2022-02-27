package slogo.InstructionClasses.MathOperations;

import slogo.InstructionClasses.Instruction;

public class Quotient extends Instruction {
  public static final int QUOTIENT_PARAM_NUM = 2;

  public Quotient() {
    super(QUOTIENT_PARAM_NUM);
  }

  @Override
  public double returnValue() {
    double param1 = getMyParameters()[0].returnValue();
    double param2 = getMyParameters()[1].returnValue();
    return param1/param2;
  }

  @Override
  public void run() {
    // do nothing as this command does not affect the turtle
  }

  @Override
  public String toString() {
    double param1 = getMyParameters()[0].returnValue();
    double param2 = getMyParameters()[1].returnValue();
    return String.format("quotient %f %f\n", param1, param2);
  }
}