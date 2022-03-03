package slogo.InstructionClasses.TurtleCommands;

import java.util.function.BiFunction;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleRecord;

public class SetHeading extends Instruction {
  public static final int SET_HEADING_PARAM_NUM = 1;

  private double oldHeading;
  private double heading;

  public SetHeading(TurtleCollection turtleModel) {
    super(SET_HEADING_PARAM_NUM, turtleModel);
  }

  /*
  private int setHeading(int[] params) {
    System.out.println("setHeading");
    heading = checkHeading(heading);
    double oldHeading = heading;
    heading = checkHeading(params[0]);
    return (int)(oldHeading - heading);
  }
   */


  @Override
  public double returnValue() {
    return oldHeading - heading;
  }

  @Override
  public BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda() {
    return (Instruction[] params, TurtleRecord myRecord) -> {
      System.out.println("setHeading");
      heading = checkHeading(myRecord.myHeading());
      oldHeading = heading;
      heading = checkHeading(params[0].returnValue());

      return new TurtleRecord(myRecord.myX(), myRecord.myY(), heading, myRecord.isPenDown(), myRecord.isShowing());
    };
  }

  private double checkHeading(double tempHeading) {
    if (tempHeading < 0) {
      return 360 + tempHeading;
    }
    else if (tempHeading > 360) {
      return 360 - tempHeading;
    }
    return tempHeading;
  }
}
