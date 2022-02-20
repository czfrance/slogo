package slogo.InstructionClasses;

public class Product extends MathTwoParam{

  @Override
  public double returnValue() {
    return getParamOne()*getParamTwo();
  }

  @Override
  public String toString() {
    return String.format("product %d %d\n", (int) getParamOne(), (int) getParamTwo());
  }
}
