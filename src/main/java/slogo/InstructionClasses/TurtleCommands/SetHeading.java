package slogo.InstructionClasses.TurtleCommands;

import java.util.function.BiFunction;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleRecord;

public class SetHeading extends TurtleCommand {

  public static final int SET_HEADING_PARAM_NUM = 1;

  public SetHeading(TurtleCollection turtleModel) {
    super(SET_HEADING_PARAM_NUM, turtleModel);
  }

  @Override
  public double returnValue() {
    double myDegrees = getMyParameters()[0].returnValue();
    return myDegrees;
  }

  @Override
  public BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda() {
    return (Instruction[] params, TurtleRecord myRecord) -> {
      double heading = myRecord.myHeading();
      heading = checkHeading(heading);
      double oldHeading = heading;
      heading = checkHeading(params[0].returnValue());
      double newHeading = oldHeading - heading;

      return new TurtleRecord(myRecord.myX(), myRecord.myY(), newHeading, myRecord.isPenDown(), myRecord.isShowing());
    };
  }
}
