package slogo.InstructionClasses.MathOperations;

public class Product extends MathTwoParam {

  @Override
  public double returnValue() {
    return getParamOne()*getParamTwo();
  }

  @Override
  public String toString() {
    return String.format("product %f %f\n", getParamOne(), getParamTwo());
  }
}
