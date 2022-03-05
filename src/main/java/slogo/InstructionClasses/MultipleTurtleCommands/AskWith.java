package slogo.InstructionClasses.MultipleTurtleCommands;

import java.util.Stack;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;

public class AskWith extends MultipleTurtleCommand{
  public static final int ASK_WITH_NUM_PARAM = 2;

  private double lastID=0;
  private Instruction turtleConditionList;
  private Instruction myInsnList;

  // bug can't create turtle using ask
  public AskWith(TurtleCollection collection) {
    super(ASK_WITH_NUM_PARAM, collection);
  }

  @Override
  public void setParameters(Stack<Instruction> valueStack) {
    myInsnList = valueStack.pop();
    turtleConditionList = valueStack.pop();
  }

  @Override
  public void run() {
    TurtleCollection turtleCollection = getMyTurtles();
    turtleCollection.clearActiveTurtles();
    turtleCollection.setActiveWithCond(turtleConditionList);

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
