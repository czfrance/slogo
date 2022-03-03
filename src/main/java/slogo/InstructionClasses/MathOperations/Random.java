package slogo.InstructionClasses.MathOperations;

import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleModel;

public class Random extends MathOperation {
  public static final int RANDOM_PARAM_NUM = 1;

  public Random(TurtleCollection turtleModel) {
    super(RANDOM_PARAM_NUM, turtleModel);
  }

  @Override
  public double returnValue() {
    double param1 = getMyParameters()[0].returnValue();
    double max = param1;
    return max*Math.random();
  }

  @Override
  public String toString() {
    double param1 = getMyParameters()[0].returnValue();
    return String.format("random %f\n", param1);
  }
}
