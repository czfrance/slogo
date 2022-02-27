package slogo.InstructionClasses.MathOperations;

import slogo.InstructionClasses.Instruction;

public class Random extends Instruction {
  public static final int RANDOM_PARAM_NUM = 1;

  public Random() {
    super(RANDOM_PARAM_NUM);
  }

  @Override
  public double returnValue() {
    double param1 = getMyParameters()[0].returnValue();
    double max = param1;
    return max*Math.random();
  }

  @Override
  public void run() {
    // do nothing as this command does not affect the turtle
  }

  @Override
  public String toString() {
    double param1 = getMyParameters()[0].returnValue();
    return String.format("random %f\n", param1);
  }
}
