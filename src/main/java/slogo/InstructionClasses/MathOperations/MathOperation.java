package slogo.InstructionClasses.MathOperations;

import java.util.function.BiFunction;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleRecord;

public abstract class MathOperation extends Instruction {

  public MathOperation(int numParam, TurtleCollection model) {
    super(numParam, model);
  }
  @Override
  public abstract double returnValue();

  @Override
  public BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda() {
    return (Instruction[] myParam, TurtleRecord myRecord) -> myRecord;
  }
}
