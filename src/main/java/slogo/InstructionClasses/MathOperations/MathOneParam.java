package slogo.InstructionClasses.MathOperations;

import java.util.Stack;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleModel;

public abstract class MathOneParam extends Instruction {
  public static final int MATHONEPARAM_NUM_PARAM = 1;
  private double myParam = 0;

  public MathOneParam(TurtleModel turtleModel) {
    super(MATHONEPARAM_NUM_PARAM, turtleModel);
  }

  @Override
  public void setParameters(Stack<Instruction> valueStack) {
    //exception if not enough parameters needed to be implemented
    myParam = valueStack.pop().returnValue();
    valueStack.push(this);
  }

  protected double getParam() {
    return myParam;
  }

  @Override
  public void run() {
    //Math operations do not directly affect the turtle so empty run method
    return;
  }
}
