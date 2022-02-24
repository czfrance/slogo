package slogo.InstructionClasses.Arithmetic;

import slogo.InstructionClasses.MathTwoParam;

public class Difference extends MathTwoParam {

  @Override
  public double returnValue() {
    return getParamOne()-getParamTwo();
  }

  @Override
  public String toString() {
    return String.format("difference %f %f\n", getParamOne(), getParamTwo());
  }
}
