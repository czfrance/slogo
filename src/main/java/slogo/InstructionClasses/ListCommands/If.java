package slogo.InstructionClasses.ListCommands;

import java.util.Stack;
import slogo.InstructionClasses.InsnList;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;

public class If extends ListCommand{
  public static final int IF_NUM_PARAMS = 2;

  private Instruction myConditional;
  private Instruction myInsnList;

  public If(TurtleCollection model) {
    super(IF_NUM_PARAMS, model);
  }

  @Override
  public void setParameters(Stack<Instruction> valueStack) {
    Instruction insnList = valueStack.pop();
    myInsnList = insnList;
    Instruction conditional = valueStack.pop();
    myConditional = conditional;
    valueStack.push(this);
  }

  @Override
  public void run() {
    if(myInsnList.getIsDone()) {
      setIsDone(true);
    }
    else if(myConditional.returnValue()!=0) {
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
    if(myConditional.returnValue()!=0) {
      return myInsnList.returnValue();
    }
    else{
      return 0;
    }
  }
}
