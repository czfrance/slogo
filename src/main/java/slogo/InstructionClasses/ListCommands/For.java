package slogo.InstructionClasses.ListCommands;

import java.util.Stack;
import java.util.function.BiFunction;
import slogo.InstructionClasses.InsnList;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleRecord;

public class For extends ListCommand {

  public static final int FOR_TIMES_PARAMS = 2;

  private double currCount = 0;
  private double myEnd = 0;
  private double myIncrement = 0;
  private Instruction myInsnList;
  private Instruction myVar;

  public For(TurtleCollection collection) {
    super(FOR_TIMES_PARAMS, collection);
  }

  @Override
  public void setParameters(Stack<Instruction> valueStack) {
    Instruction insnList = valueStack.pop();
    myInsnList = insnList;
    Instruction limitList = valueStack.pop();
    myVar = limitList.getParamNum(0);
    currCount = limitList.getParamNum(1).returnValue();
    myEnd = limitList.getParamNum(2).returnValue();
    myIncrement = limitList.getParamNum(3).returnValue();
    valueStack.push(this);
  }

  @Override
  public void run() {
    if(myInsnList.getIsDone()) {
      currCount += myIncrement;
      setVarVal(myVar, currCount);
      myInsnList = new InsnList(myInsnList);
    }
    if(currCount <= myEnd) {
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
