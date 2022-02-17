package slogo.InstructionClasses;

import java.util.EmptyStackException;
import java.util.Stack;

public class Forward extends Command {
  private double pixels = 0;

  @Override
  public void setParameters(Stack<String> valueStack) {
    String currParam = valueStack.pop();
    /*
    if(!isValueType("Constant", currParam)) {
      throw BadArgumentException;
    }
    */
    pixels = Double.parseDouble(currParam);
    valueStack.push(Double.toString(returnValue()));
  }

  @Override
  public double returnValue() {
    return pixels;
  }
}
