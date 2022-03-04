package slogo.InstructionClasses.ListCommands;

import java.util.Stack;
import java.util.function.BiFunction;
import slogo.InstructionClasses.Constant;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleRecord;

public abstract class ListCommand extends Instruction {

  public ListCommand(int numParam, TurtleCollection collection) {
    super(numParam, collection);
  }
  @Override
  public abstract double returnValue();

  @Override
  public BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda() {
    return (Instruction[] myParam, TurtleRecord myRecord) -> myRecord;
  }

  protected void setVarVal(Instruction variable, double val) {
    Stack valStack = new Stack<Instruction>();
    Constant valConstant = new Constant(Double.toString(val));
    valStack.push(valConstant);
    variable.setParameters(valStack);
  }
}
