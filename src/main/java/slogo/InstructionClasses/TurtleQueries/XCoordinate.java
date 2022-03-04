package slogo.InstructionClasses.TurtleQueries;

import java.util.function.BiFunction;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleRecord;

public class XCoordinate extends TurtleQuery {

  public XCoordinate(TurtleCollection model) {
    super(model);
  }

  @Override
  public double returnValue() {
    return getCurrTurtleRecord().myX();
  }
}
