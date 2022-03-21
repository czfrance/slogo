package slogo.InstructionClasses.MultipleTurtleCommands;

import slogo.Model.TurtleCollection;

public class Turtles extends MultipleTurtleCommand{
  public static final int TURTLES_NUM_PARAM = 0;

  public Turtles(TurtleCollection collection) {
    super(TURTLES_NUM_PARAM, collection);
  }

  @Override
  public double returnValue() {
    System.out.println(getMyTurtles().getTotalActiveTurtles());
    return getMyTurtles().getTotalActiveTurtles();
  }
}
