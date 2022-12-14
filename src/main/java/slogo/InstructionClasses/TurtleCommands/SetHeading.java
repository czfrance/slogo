package slogo.InstructionClasses.TurtleCommands;

import java.util.function.BiFunction;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleRecord;

public class SetHeading extends TurtleCommand {
  public static final int SET_HEADING_PARAM_NUM = 1;

  private double oldHeading;
  private double heading;

  public SetHeading(TurtleCollection turtleModel) {
    super(SET_HEADING_PARAM_NUM, turtleModel);
  }


  @Override
  public double returnValue() {
    return oldHeading - heading;
  }

  @Override
  public BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda() {
    return (Instruction[] params, TurtleRecord myRecord) -> {
      heading = checkHeading(myRecord.myHeading());
      oldHeading = heading;
      heading = checkHeading(params[0].returnValue());

      return new TurtleRecord(myRecord.myX(), myRecord.myY(), heading, myRecord.isPenDown(), myRecord.isShowing());
    };
  }
}
