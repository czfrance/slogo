package slogo.InstructionClasses;

import java.util.Stack;
import slogo.InstructionClasses.Instruction;

public class Forward extends Instruction {
  private double myPixels = 0;
  public static final int FORWARD_PARAM_NUM = 1;

  public Forward() {
    super(FORWARD_PARAM_NUM);
  }

  @Override
  public void setParameters(Stack<Instruction> valueStack) {
    Instruction currParam = valueStack.pop();
    /*
    if(!isValueType("Constant", currParam)) {
      throw BadArgumentException;
    }
    */
    myPixels = currParam.returnValue();
    valueStack.push(this);
  }

  @Override
  public double returnValue() {
    return myPixels;
  }

  @Override
  public void run() {

  }

  @Override
  public String toString() {
    return String.format("forward %d\n", (int) myPixels);
  }
}
