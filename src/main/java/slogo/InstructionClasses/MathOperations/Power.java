package slogo.InstructionClasses.MathOperations;

public class Power extends MathTwoParam {

  @Override
  public double returnValue() {
    return Math.pow(getParamOne(), getParamTwo());
  }

  @Override
  public String toString() {
    return String.format("power %f %f\n", getParamOne(), getParamTwo());
  }
}
