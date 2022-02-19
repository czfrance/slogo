package slogo.InstructionClasses;

public class Sum extends MathTwoParam{

  @Override
  public double returnValue() {
    return getParamOne()+getParamTwo();
  }
}
