package slogo.InstructionClasses.TurtleQueries;

import java.util.function.BiFunction;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleModel;
import slogo.Model.TurtleRecord;

public abstract class TurtleQuery extends Instruction {

  public static final int TURTLE_QUERY_PARAM_NUM = 0;
  public TurtleQuery(TurtleCollection model) {
    super(TURTLE_QUERY_PARAM_NUM, model);
  }

  @Override
  public abstract double returnValue();

  @Override
  public BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda() {
    return (Instruction[] myParam, TurtleRecord myRecord) -> myRecord;
  }

  protected TurtleRecord getCurrTurtleRecord() {
    return getMyTurtles().getActiveTurtle().getTurtleRecord();
  }
}
