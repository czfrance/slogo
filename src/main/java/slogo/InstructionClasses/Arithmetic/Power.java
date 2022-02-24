package slogo.InstructionClasses.Arithmetic;

import slogo.InstructionClasses.Arithmetic.MathTwoParam;

public class Power extends MathTwoParam {

  @Override
  public double returnValue() {
    return Math.pow(getParamOne(), getParamTwo());
  }

  @Override
  public String toString() {
    return String.format("power %f %f\n", getParamOne(), getParamTwo());
  }
}
