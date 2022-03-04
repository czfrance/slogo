package slogo.InstructionClasses.ListCommands;

import java.util.Stack;
import java.util.function.BiFunction;
import slogo.InstructionClasses.Constant;
import slogo.InstructionClasses.InsnList;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleRecord;

public class Repeat extends ListCommand {

  public static final int REPEAT_NUM_PARAMS = 2;

  private double numIterations = 0;

  private int currIteration = 0;

  private Instruction myInsnList;

  public Repeat(TurtleCollection model) {
    super(REPEAT_NUM_PARAMS, model);
  }

  @Override
  public void setParameters(Stack<Instruction> valueStack) {
    Instruction insnList = valueStack.pop();
    myInsnList = insnList;
    Instruction expr = valueStack.pop();
    numIterations = expr.returnValue();
    valueStack.push(this);
  }

  @Override
  public void run() {
    if(myInsnList.getIsDone()) {
      currIteration++;
      myInsnList = new InsnList(myInsnList);
    }
    if(currIteration<=numIterations) {
      myInsnList.run();
    }
    else{
      setIsDone(true);
    }
  }

  @Override
  public double frontEndReturnValue() {
    return myInsnList.frontEndReturnValue();
  }

  @Override
  public double returnValue() {
    return myInsnList.returnValue();
  }
}
