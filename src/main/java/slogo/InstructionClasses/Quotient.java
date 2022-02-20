package slogo.InstructionClasses;

public class Quotient extends MathTwoParam{

  @Override
  public double returnValue() {
    return getParamOne()/getParamTwo();
  }
}
