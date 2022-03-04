package slogo.InstructionClasses.MultipleTurtleCommands;

import java.util.Stack;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;

public class Tell extends MultipleTurtleCommand{

  public static final int TELL_NUM_PARAM = 1;

  private double lastID=0;
  private Instruction turtleIDList;

  public Tell(TurtleCollection collection) {
    super(TELL_NUM_PARAM, collection);
  }

  @Override
  public void setParameters(Stack<Instruction> valueStack) {
    turtleIDList = valueStack.pop();
  }

  @Override
  public void run() {
    TurtleCollection turtleCollection = getMyTurtles();
    turtleCollection.clearTellTurtles();
    while(!turtleIDList.getIsDone()) {
      turtleIDList.run();
      turtleCollection.addTellTurtle((int) turtleIDList.frontEndReturnValue());
      lastID = turtleIDList.frontEndReturnValue();
    }
    turtleCollection.resetActiveToTellTurtles();
    setIsDone(true);
  }

  @Override
  public double returnValue() {
    return lastID;
  }
}
