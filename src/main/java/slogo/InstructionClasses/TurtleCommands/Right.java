package slogo.InstructionClasses.TurtleCommands;

import java.util.Stack;
import slogo.InstructionClasses.Instruction;

public class Right extends Instruction {
  private double myDegrees = 0;
  public static final int RIGHT_PARAM_NUM = 1;

  public Right() {
    super(RIGHT_PARAM_NUM);
  }

  @Override
  public void setParameters(Stack<Instruction> valueStack) {
    Instruction currParam = valueStack.pop();
    /*
    if(!isValueType("Constant", currParam)) {
      throw BadArgumentException;
    }
    */
    myDegrees = currParam.returnValue();
    valueStack.push(this);
  }

  @Override
  public double returnValue() {
    return myDegrees;
  }

  @Override
  public void run() {

  }

  @Override
  public String toString() {
    return String.format("right %f\n", myDegrees);
  }
}
