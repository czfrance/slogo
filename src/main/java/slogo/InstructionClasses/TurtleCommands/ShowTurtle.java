package slogo.InstructionClasses.TurtleCommands;

import java.util.function.BiFunction;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleRecord;

public class ShowTurtle extends TurtleCommand{
  public static final int SHOW_TURTLE_PARAM_NUM = 0;

  public ShowTurtle(TurtleCollection turtleModel) {
    super(SHOW_TURTLE_PARAM_NUM, turtleModel);
  }

  @Override
  public BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda() {
    return (Instruction[] params, TurtleRecord myRecord) -> {
      return new TurtleRecord(myRecord.myX(), myRecord.myY(), myRecord.myHeading(),
          myRecord.isPenDown(), true);
    };
  }

  @Override
  public double returnValue() {
    return 1;
  }
}
