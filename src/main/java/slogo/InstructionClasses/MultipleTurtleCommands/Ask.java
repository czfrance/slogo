package slogo.InstructionClasses.MultipleTurtleCommands;

import java.util.Stack;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;

public class Ask extends MultipleTurtleCommand{
  public static final int TELL_NUM_PARAM = 2;

  private double lastID=0;
  private Instruction turtleIDList;
  private Instruction myInsnList;

  // bug can't create turtle using ask
  public Ask(TurtleCollection collection) {
    super(TELL_NUM_PARAM, collection);
  }

  @Override
  public void setParameters(Stack<Instruction> valueStack) {
    turtleIDList = valueStack.pop();
    myInsnList = valueStack.pop();
  }

  @Override
  public void run() {
    TurtleCollection turtleCollection = getMyTurtles();
    turtleCollection.clearActiveTurtles();
    while(!turtleIDList.getIsDone()) {
      turtleIDList.run();
      turtleCollection.addActiveTurtle((int) turtleIDList.frontEndReturnValue());
      lastID = turtleIDList.frontEndReturnValue();
    }

    myInsnList.run();

    if(myInsnList.getIsDone()) {
      turtleCollection.resetActiveToTellTurtles();
      setIsDone(true);
    }
  }

  @Override
  public double returnValue() {
    return lastID;
  }
}
