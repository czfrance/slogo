package slogo.InstructionClasses.BooleanOperations;

import slogo.Model.TurtleCollection;

public class NotEqual extends BooleanOperation {
  public static final int NOT_EQUAL_PARAM_NUM = 2;

  public NotEqual(TurtleCollection turtleModel) {
    super(NOT_EQUAL_PARAM_NUM, turtleModel);
  }

  @Override
  public double returnValue() {
    double param1 = getMyParameters()[0].returnValue();
    double param2 = getMyParameters()[1].returnValue();
    if(!(Math.abs(param1 - param2)<=0.0001)) {
      return 1;
    }
    else {
      return 0;
    }
  }
}
