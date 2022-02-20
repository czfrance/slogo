package slogo.InstructionClasses;

public class Product extends MathTwoParam{

  @Override
  public double returnValue() {
    return getParamOne()*getParamTwo();
  }
}
