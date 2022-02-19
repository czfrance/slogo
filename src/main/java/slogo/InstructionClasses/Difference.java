package slogo.InstructionClasses;

public class Difference extends MathTwoParam{

  @Override
  public double returnValue() {
    return getParamOne()-getParamTwo();
  }
}
