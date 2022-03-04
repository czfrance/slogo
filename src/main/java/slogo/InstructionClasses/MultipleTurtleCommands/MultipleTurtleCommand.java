package slogo.InstructionClasses.MultipleTurtleCommands;

import java.util.function.BiFunction;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleRecord;

public abstract class MultipleTurtleCommand extends Instruction {

  public MultipleTurtleCommand(int numParam, TurtleCollection collection) {
    super(numParam, collection);
  }

  @Override
  public abstract double returnValue();

  @Override
  public BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda() {
    return (Instruction[] params, TurtleRecord myRecord) -> myRecord;
  }
}
