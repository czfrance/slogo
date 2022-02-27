package slogo.InstructionClasses.MathOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleModel;

public abstract class MathTwoParam extends Instruction {
  public static final int MATHTWOPARAM_NUM_PARAM = 2;
  private List<Instruction> myExpressions = new ArrayList<Instruction>();

  public MathTwoParam(TurtleModel turtleModel) {
    super(MATHTWOPARAM_NUM_PARAM, turtleModel);
  }

  @Override
  public void setParameters(Stack<Instruction> valueStack) {
    //exception if not enough parameters needed to be implemented
    while(myExpressions.size()<MATHTWOPARAM_NUM_PARAM) {
      myExpressions.add(valueStack.pop());
    }
    valueStack.push(this);
  }

  protected double getParamOne() {
    return myExpressions.get(0).returnValue();
  }

  protected double getParamTwo() {
    return myExpressions.get(1).returnValue();
  }

  @Override
  public void run() {
    //Math operations do not directly affect the turtle so empty run method
    return;
  }
}
