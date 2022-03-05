package slogo.InstructionClasses.TurtleCommands;

import java.util.function.BiFunction;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleModel;
import slogo.Model.TurtleRecord;

//bugged
public class SetTowards extends TurtleCommand{
  public static final int TOWARDS_PARAM_NUM = 2;
  private double oldHeading;
  private double heading;

  public SetTowards(TurtleCollection turtleModel) {
    super(TOWARDS_PARAM_NUM, turtleModel);
  }

  /*
    System.out.println("towards");
    heading = checkHeading(heading);
    double oldHeading = heading;
    double newHeading = calcAbsHeading(params[0], params[1]);
    heading = newHeading;
    //heading = checkHeading(newHeading);
    return (int)(oldHeading - heading);
   */

  @Override
  public BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda() {
    return (Instruction[] params, TurtleRecord myRecord) -> {
      System.out.println("towards");
      heading = checkHeading(myRecord.myHeading());
      oldHeading = heading;
      double newHeading = calcAbsHeading(params[0].returnValue(), params[1].returnValue(), myRecord);
      heading = newHeading;

      return new TurtleRecord(myRecord.myX(), myRecord.myY(), heading, myRecord.isPenDown(), myRecord.isShowing());
    };
  }

  @Override
  public double returnValue() {
    return oldHeading-heading;
  }

  private double calcAbsHeading(double x, double y, TurtleRecord myRecord) {
    double myX = myRecord.myX();
    double myY = myRecord.myY();

    if (x == myX && y == myY) {
      return heading;
    }

    double angleToX = calcAngleToX(x, y, myX, myY);
    int quadrant = findQuadrant(x, y);

    switch (quadrant) {
      case 1 -> {return angleToX;}
      case 2 -> {return 180-angleToX;}
      case 3 -> {return 180+angleToX;}
      default -> {return 360-angleToX;}
    }
  }
}
