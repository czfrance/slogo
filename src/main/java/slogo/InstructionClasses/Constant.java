package slogo.InstructionClasses;

import java.util.Stack;
import java.util.function.BiFunction;
import slogo.Model.TurtleRecord;

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
  public void run() {
    setIsDone(true);
    System.out.println("constant Ran");
  }

  @Override
  public double returnValue() {
    return myConstantValue;
  }

  @Override
  public BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda() {
    return (Instruction[] params, TurtleRecord myRecord) -> myRecord;
  }
}
