package slogo.InstructionClasses.MathOperations;

import slogo.Model.TurtleCollection;

public class NaturalLog extends MathOperation {

  public static final int LOG_PARAM_NUM = 1;

  public NaturalLog(TurtleCollection turtleModel) {
    super(LOG_PARAM_NUM, turtleModel);
  }

  @Override
  public double returnValue() {
    double param1 = getMyParameters()[0].returnValue();
    return Math.log(param1);
  }

}
