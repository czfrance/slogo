package slogo.InstructionClasses.TurtleCommands;

import slogo.InstructionClasses.Instruction;

public class Forward extends Instruction {
  public static final int FORWARD_PARAM_NUM = 1;

  public Forward() {
    super(FORWARD_PARAM_NUM);
  }

  /*
  @Override
  public void setParameters(Stack<Instruction> valueStack) {
    Instruction currParam = valueStack.pop();
    /*
    if(!isValueType("Constant", currParam)) {
      throw BadArgumentException;
    }

    getMyParameters()[0] = currParam;
    valueStack.push(this);
  }
  */

  @Override
  public double returnValue() {
    double myPixels = getMyParameters()[0].returnValue();
    return myPixels;
  }

  @Override
  public void run() {
      //consumer = (List<Double> params, ) -> {currX = currX+myPixels}
  }

  @Override
  public String toString() {
    double myPixels = getMyParameters()[0].returnValue();
    return String.format("forward %f\n", myPixels);
  }
}
