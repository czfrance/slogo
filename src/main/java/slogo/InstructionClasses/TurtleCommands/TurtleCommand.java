package slogo.InstructionClasses;

import java.util.Stack;
import slogo.InstructionClasses.Instruction;

public abstract class TurtleCommand extends Instruction {

  @Override
  public void setParameters(Stack<Instruction> valueStack) {

  }

  @Override
  public double returnValue() {
    return 0;
  }

}
