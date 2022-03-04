package slogo.InstructionClasses.BooleanOperations;

import slogo.Model.TurtleCollection;

public class Or extends BooleanOperation {
  public static final int OR_PARAM_NUM = 2;

  public Or(TurtleCollection turtleModel) {
    super(OR_PARAM_NUM, turtleModel);
  }

  @Override
  public double returnValue() {
    double param1 = getMyParameters()[0].returnValue();
    double param2 = getMyParameters()[1].returnValue();
    if(Math.abs(param1)>0.0001 || Math.abs(param2)>0.0001) {
      return 1;
    }
    else {
      return 0;
    }
  }
}
