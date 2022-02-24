package slogo.InstructionClasses.Arithmetic;

public class Difference extends MathTwoParam {

  @Override
  public double returnValue() {
    return getParamOne()-getParamTwo();
  }

  @Override
  public String toString() {
    return String.format("difference %f %f\n", getParamOne(), getParamTwo());
  }
}
