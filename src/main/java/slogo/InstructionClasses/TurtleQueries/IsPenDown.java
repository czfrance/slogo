package slogo.InstructionClasses.TurtleQueries;

import slogo.Model.TurtleCollection;

public class IsPenDown extends TurtleQuery{

  public IsPenDown(TurtleCollection model) {
    super(model);
  }

  @Override
  public double returnValue() {
    if(getCurrTurtleRecord().isPenDown()){
      return 1;
    }
    else{
      return 0;
    }
  }
}
