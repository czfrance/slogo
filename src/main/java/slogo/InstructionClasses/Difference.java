package slogo.InstructionClasses;

public class Difference extends MathTwoParam{

  @Override
  public double returnValue() {
    return getParamOne()-getParamTwo();
  }

  @Override
  public String toString() {
    return String.format("difference %d %d\n", (int) getParamOne(), (int) getParamTwo());
  }
}
