package slogo.InstructionClasses.BooleanOperations;

import slogo.Model.TurtleCollection;

public class Not extends BooleanOperation {
  public static final int NOT_PARAM_NUM = 1;

  public Not(TurtleCollection turtleModel) {
    super(NOT_PARAM_NUM, turtleModel);
  }

  @Override
  public double returnValue() {
    double param1 = getMyParameters()[0].returnValue();
    if(Math.abs(param1)<=0.0001) {
      return 1;
    }
    else {
      return 0;
    }
  }
}
