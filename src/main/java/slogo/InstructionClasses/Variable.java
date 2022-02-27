package slogo.InstructionClasses;

import java.util.Stack;
import java.util.function.BiFunction;
import slogo.Model.TurtleModel;
import slogo.Model.TurtleRecord;

public class Variable extends Instruction{
  public static final int VARIABLE_PARAM_NUM = 1;

  private String myName;
  public Variable(String name, TurtleModel turtleModel) {
    super(VARIABLE_PARAM_NUM, turtleModel);
    myName = name;
  }


  @Override
  public double returnValue() {
    return getMyParameters()[0].returnValue();
  }

  @Override
  public BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda() {
    return (Instruction[] params, TurtleRecord myRecord) -> myRecord;
  }

  @Override
  public String toString() {
    double myVal = getMyParameters()[0].returnValue();
    return String.format("make %s %f\n", myName, returnValue());
  }
}
