package slogo.InstructionClasses;

public class Quotient extends MathTwoParam{

  @Override
  public double returnValue() {
    return getParamOne()/getParamTwo();
  }

  @Override
  public String toString() {
    return String.format("quotient %d %d\n", (int) getParamOne(), (int) getParamTwo());
  }
}
