package slogo.InstructionClasses.BooleanOperations;

import slogo.Model.TurtleCollection;

public class GreaterThan extends BooleanOperation {
  public static final int GREATER_THAN_PARAM_NUM = 2;

  public GreaterThan(TurtleCollection turtleModel) {
    super(GREATER_THAN_PARAM_NUM, turtleModel);
  }

  @Override
  public double returnValue() {
    double param1 = getMyParameters()[0].returnValue();
    double param2 = getMyParameters()[1].returnValue();
    if(param1 > param2) {
      return 1;
    }
    else {
      return 0;
    }
  }
}
