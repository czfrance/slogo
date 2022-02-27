package slogo.InstructionClasses.TurtleCommands;

import java.util.Stack;
import slogo.InstructionClasses.Instruction;

public abstract class TurtleCommand extends Instruction {


  @Override
  public void setParameters(Stack<Instruction> valueStack) {
    int currParamIndex = getNumParameters()-1;
    for(int i = currParamIndex; i>=0; i--) {
      Instruction currParam = valueStack.pop();
      getMyParameters()[i] = currParam;
    }
    valueStack.push(this);
  }

  @Override
  public double returnValue() {
    return 0;
  }

}
