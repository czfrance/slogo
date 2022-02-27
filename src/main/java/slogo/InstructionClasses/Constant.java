package slogo.InstructionClasses;

import java.util.Stack;

public class Constant extends Instruction {

  private Double myConstantValue;

  public Constant(String value) {
    super();
    myConstantValue = Double.parseDouble(value);
  }

  @Override
  public void setParameters(Stack<Instruction> valueStack) {
    // Constants have no parameters so empty parameter
  }


  @Override
  public double returnValue() {
    return myConstantValue;
  }

  @Override
  public void run() {
    //functionlainterface = forward STuff;
    //turtle.doStuff(functionalInterface);
  }
}
