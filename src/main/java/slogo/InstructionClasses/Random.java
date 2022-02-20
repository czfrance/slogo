package slogo.InstructionClasses;

public class Random extends MathOneParam{

  @Override
  public double returnValue() {
    double max = getParam();
    return max*Math.random();
  }

  @Override
  public String toString() {
    return String.format("random %f\n", getParam());
  }
}
