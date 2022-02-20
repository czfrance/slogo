package slogo.InstructionClasses;

import java.util.Stack;
import slogo.InstructionClasses.Instruction;

public class Backward extends Instruction {
  public static final int BACKWARD_NUM_PARAM = 1;


  private double myPixels = 0;
  public Backward() {
    super(BACKWARD_NUM_PARAM);
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
    return String.format("back %f\n", myPixels);
  }
}
