package slogo.InstructionClasses;

import java.util.Stack;

public class Constant extends Command{

  private Double myConstantValue;

  public Constant(String value) {
    myConstantValue = Double.parseDouble(value);
  }

  @Override
  public void setParameters(Stack<Command> valueStack) {
    // Constants have no parameters so empty parameter
  }

  @Override
  public int getNumParameters() {
    return 0;
  }

  @Override
  public double returnValue() {
    return myConstantValue;
  }
}
