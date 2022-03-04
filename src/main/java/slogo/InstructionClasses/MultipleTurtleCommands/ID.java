package slogo.InstructionClasses.MultipleTurtleCommands;

import slogo.Model.TurtleCollection;

public class ID extends MultipleTurtleCommand{

  public static final int ID_NUM_PARAM = 0;

  public ID(TurtleCollection collection) {
    super(ID_NUM_PARAM, collection);
  }

  @Override
  public double returnValue() {
    return getMyTurtles().getActiveTurtleID();
  }
}
