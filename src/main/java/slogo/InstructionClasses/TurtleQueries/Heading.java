package slogo.InstructionClasses.TurtleQueries;

import slogo.Model.TurtleCollection;

public class Heading extends TurtleQuery{

  public Heading(TurtleCollection model) {
    super(model);
  }

  @Override
  public double returnValue() {
    return getCurrTurtleRecord().myHeading();
  }
}
