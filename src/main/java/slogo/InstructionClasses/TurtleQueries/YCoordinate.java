package slogo.InstructionClasses.TurtleQueries;

import slogo.Model.TurtleCollection;

public class YCoordinate extends TurtleQuery{

  public YCoordinate(TurtleCollection model) {
    super(model);
  }

  @Override
  public double returnValue() {
    return getCurrTurtleRecord().myY();
  }
}
