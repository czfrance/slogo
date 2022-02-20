package slogo.InstructionClasses;

public class Power extends MathTwoParam{

  @Override
  public double returnValue() {
    return Math.pow(getParamOne(), getParamTwo());
  }

  @Override
  public String toString() {
    return String.format("power %d %d\n", (int) getParamOne(), (int) getParamTwo());
  }
}
