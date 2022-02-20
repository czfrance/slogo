package slogo.InstructionClasses;

public class Sum extends MathTwoParam{

  @Override
  public double returnValue() {
    return getParamOne()+getParamTwo();
  }

  @Override
  public String toString() {
    return String.format("sum %f %f\n", getParamOne(), getParamTwo());
  }
}
