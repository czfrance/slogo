package slogo.InstructionClasses.MathOperations;

import java.util.function.BiFunction;
import slogo.InstructionClasses.Instruction;
import slogo.InstructionClasses.TurtleCommands.TurtleCommand;
import slogo.Model.TurtleModel;
import slogo.Model.TurtleRecord;

public abstract class MathOperation extends Instruction {

  public MathOperation(int numParam, TurtleModel model) {
    super(numParam, model);
  }
  @Override
  public abstract double returnValue();

  @Override
  public BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda() {
    return (Instruction[] myParam, TurtleRecord myRecord) -> myRecord;
  }
}
