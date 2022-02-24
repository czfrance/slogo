package slogo.InstructionClasses.Arithmetic;

import slogo.InstructionClasses.Arithmetic.MathTwoParam;

public class Sum extends MathTwoParam {

  @Override
  public double returnValue() {
    return getParamOne()+getParamTwo();
  }

  @Override
  public String toString() {
    return String.format("sum %f %f\n", getParamOne(), getParamTwo());
  }
}
