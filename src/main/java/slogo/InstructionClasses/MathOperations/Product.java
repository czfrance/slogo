package slogo.InstructionClasses.MathOperations;

import slogo.InstructionClasses.Instruction;

public class Product extends Instruction {
  public static final int PRODUCT_PARAM_NUM = 2;

  public Product() {
    super(PRODUCT_PARAM_NUM);
  }

  @Override
  public double returnValue() {
    double param1 = getMyParameters()[0].returnValue();
    double param2 = getMyParameters()[1].returnValue();
    return param1*param2;
  }

  @Override
  public void run() {
    // do nothing as this command does not affect the turtle
  }

  @Override
  public String toString() {
    double param1 = getMyParameters()[0].returnValue();
    double param2 = getMyParameters()[1].returnValue();
    return String.format("product %f %f\n", param1, param2);
  }
}
