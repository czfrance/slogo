package slogo.InstructionClasses.TurtleCommands;

import java.util.function.BiFunction;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleModel;
import slogo.Model.TurtleRecord;

public class Right extends TurtleCommand {
  public static final int RIGHT_PARAM_NUM = 1;

  public Right(TurtleCollection turtleModel) {
    super(RIGHT_PARAM_NUM, turtleModel);
  }

  @Override
  public double returnValue() {
    double myDegrees = getMyParameters()[0].returnValue();
    return myDegrees;
  }

//  @Override
//  public double frontEndReturnValue() {
//    return returnValue();
//  }

  @Override
  public BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda() {
    return (Instruction[] params, TurtleRecord myRecord) -> {
      System.out.println("right");
      double heading = myRecord.myHeading();
      heading = checkHeading(heading);
      double degrees = params[0].returnValue();
      heading = heading - degrees;

      return new TurtleRecord(myRecord.myX(), myRecord.myY(), heading, myRecord.isPenDown(), myRecord.isShowing());
    };
  }

  @Override
  public String toString() {
    double myDegrees = getMyParameters()[0].returnValue();
    return String.format("right %f\n", myDegrees);
  }
}
