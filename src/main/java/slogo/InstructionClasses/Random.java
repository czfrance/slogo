package slogo.InstructionClasses.MathOperations;

public class Random extends MathOneParam{

  @Override
  public double returnValue() {
    double max = getParam();
    return max*Math.random();
  }
}
