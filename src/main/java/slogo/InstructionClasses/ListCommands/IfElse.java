package slogo.InstructionClasses.ListCommands;

import java.util.Stack;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;

public class IfElse extends ListCommand {
  public static final int IF_ELSE_NUM_PARAMS = 3;

  private Instruction myConditional;
  private Instruction myIfInsnList;
  private Instruction myElseInsnList;

  public IfElse(TurtleCollection model) {
    super(IF_ELSE_NUM_PARAMS, model);
  }

  @Override
  public void setParameters(Stack<Instruction> valueStack) {
    Instruction elseInsnList = valueStack.pop();
    myElseInsnList = elseInsnList;
    Instruction ifInsnList = valueStack.pop();
    myIfInsnList = ifInsnList;
    Instruction conditional = valueStack.pop();
    myConditional = conditional;
    valueStack.push(this);
  }

  @Override
  public void run() {
    Instruction listToRun;
    listToRun = getListToRun();
    if(listToRun.getIsDone()) {
      setIsDone(true);
    }
      listToRun.run();
  }

  private Instruction getListToRun() {
    Instruction listToRun;
    if(myConditional.returnValue()!=0) {
      listToRun = myIfInsnList;
    }
    else{
      listToRun = myElseInsnList;
    }
    return listToRun;
  }

  @Override
  public double frontEndReturnValue() {
    return getListToRun().frontEndReturnValue();
  }

  @Override
  public double returnValue() {
    return getListToRun().returnValue();
  }

}
