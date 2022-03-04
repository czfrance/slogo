package slogo.InstructionClasses.ListCommands;

import java.util.Stack;
import java.util.function.BiFunction;
import slogo.InstructionClasses.Constant;
import slogo.InstructionClasses.InsnList;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleRecord;

public class DoTimes extends ListCommand {

  public static final int DO_TIMES_PARAMS = 2;

  private int currIteration = 0;
  private int limit;
  private Instruction myInsnList;
  private Instruction myVar;

  public DoTimes(TurtleCollection collection) {
    super(DO_TIMES_PARAMS, collection);
  }

  @Override
  public void setParameters(Stack<Instruction> valueStack) {
    Instruction insnList = valueStack.pop();
    myInsnList = insnList;
    Instruction limitList = valueStack.pop();
    myVar = limitList.getParamNum(0);
    limit = (int) limitList.getParamNum(1).returnValue();
    valueStack.push(this);
  }

  @Override
  public void run() {
    if(myInsnList.getIsDone()) {
      currIteration++;
      setVarVal(myVar, currIteration);
      myInsnList = new InsnList(myInsnList);
    }
    if(currIteration<=limit) {
      myInsnList.run();

    }
    else{
      setIsDone(true);
    }
  }

  @Override
  public double frontEndReturnValue() {
    System.out.format("frontEndValue: %f\n", myInsnList.frontEndReturnValue());
    return myInsnList.frontEndReturnValue();
  }

  @Override
  public double returnValue() {
    return myInsnList.returnValue();
  }
}
