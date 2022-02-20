package slogo.InstructionClasses;

public class Power extends MathTwoParam{

  @Override
  public double returnValue() {
    return Math.pow(getParamOne(), getParamTwo());
  }
}
