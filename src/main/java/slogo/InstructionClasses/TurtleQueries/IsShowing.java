package slogo.InstructionClasses.TurtleQueries;

import slogo.Model.TurtleCollection;

public class IsShowing extends TurtleQuery{
  public IsShowing(TurtleCollection model) {
    super(model);
  }

  @Override
  public double returnValue() {
    if(getCurrTurtleRecord().isShowing()){
      return 1;
    }
    else{
      return 0;
    }
  }
}
