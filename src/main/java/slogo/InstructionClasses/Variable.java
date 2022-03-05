package slogo.InstructionClasses;

import java.util.Stack;
import java.util.function.BiFunction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleModel;
import slogo.Model.TurtleRecord;

public class Variable extends Instruction{
  public static final int VARIABLE_PARAM_NUM = 1;

  private String myName;
  public Variable(String name, TurtleCollection turtleModel) {
    super(VARIABLE_PARAM_NUM, turtleModel);
    myName = name;
    setVariable(0.0);
  }

  public void setVariable(Double val) {
    getMyParameters()[0] = new Constant(Double.toString(val));
  }

  @Override
  public double returnValue() {
    return getMyParameters()[0].returnValue();
  }

  @Override
  public BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda() {
    return (Instruction[] params, TurtleRecord myRecord) -> myRecord;
  }

  public String valToString(){
    return ""+getMyParameters()[0].returnValue();
  }
  @Override
  public String toString() {
    double myVal = getMyParameters()[0].returnValue();
    return String.format("make %s %f\n", myName, returnValue());
  }
}
