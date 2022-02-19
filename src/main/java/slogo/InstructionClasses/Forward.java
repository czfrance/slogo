package slogo.InstructionClasses;

import java.util.EmptyStackException;
import java.util.Stack;

public class Forward extends Command {
  private double pixels = 0;
  private static final int numParameters = 1;

  @Override
  public void setParameters(Stack<Command> valueStack) {
    Command currParam = valueStack.pop();
    /*
    if(!isValueType("Constant", currParam)) {
      throw BadArgumentException;
    }
    */
    pixels = currParam.returnValue();
    valueStack.push(this);
  }

  @Override
  public int getNumParameters() {
    return numParameters;
  }

  @Override
  public double returnValue() {
    return pixels;
  }

  @Override
  public String toString() {
    return String.format("forward %f", pixels);
  }
}
