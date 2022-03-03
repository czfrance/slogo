package slogo.InstructionClasses.TurtleCommands;

import java.util.function.BiFunction;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleModel;
import slogo.Model.TurtleRecord;

public class Left extends Instruction {
  public static final int LEFT_PARAM_NUM = 1;

  public Left(TurtleModel turtleModel) {
    super(LEFT_PARAM_NUM, turtleModel);
  }

  @Override
  public double returnValue() {
    double myDegrees = getMyParameters()[0].returnValue();
    return myDegrees;
  }

  @Override
  public double frontEndReturnValue() {
    return -1*returnValue();
  }

  @Override
  public BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda() {
    return (Instruction[] params, TurtleRecord myRecord) -> {
      System.out.println("left");
      double heading = myRecord.myHeading();
      heading = checkHeading(heading);
      double degrees = params[0].returnValue();
      heading = heading + degrees;

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

  @Override
  public String toString() {
    double myDegrees = getMyParameters()[0].returnValue();
    return String.format("left %f\n", myDegrees);
  }
}
