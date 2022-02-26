package slogo.InstructionClasses.MathOperations;

public class Quotient extends MathTwoParam {

  @Override
  public double returnValue() {
    return getParamOne()/getParamTwo();
  }

  @Override
  public String toString() {
    return String.format("quotient %f %f\n", getParamOne(), getParamTwo());
  }
}
