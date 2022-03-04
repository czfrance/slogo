package slogo.InstructionClasses.BooleanOperations;

import slogo.Model.TurtleCollection;

public class LessThan extends BooleanOperation {

  public static final int LESS_THAN_PARAM_NUM = 2;

  public LessThan(TurtleCollection turtleModel) {
    super(LESS_THAN_PARAM_NUM, turtleModel);
  }

  @Override
  public double returnValue() {
    double param1 = getMyParameters()[0].returnValue();
    double param2 = getMyParameters()[1].returnValue();
    if(param1 < param2) {
      return 1;
    }
    else {
      return 0;
    }
  }
}
