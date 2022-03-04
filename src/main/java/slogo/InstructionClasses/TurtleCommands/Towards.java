package slogo.InstructionClasses.TurtleCommands;

import java.util.function.BiFunction;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleRecord;

public class Towards extends TurtleCommand{
  public static final int TOWARDS_PARAM_NUM = 2;
  private double oldHeading;
  private double heading;

  public Towards(TurtleCollection turtleModel) {
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

  private double calcAngleToX(double x, double y, double myX, double myY) {
    double xDist = Math.abs(myX - x);
    double yDist = Math.abs(myY - y);

    if (xDist == 0) {return 90;}

    return Math.toDegrees(Math.atan(yDist/xDist));
  }

  private int findQuadrant(double x, double y) {
    if (x >= 0 && y >= 0) { //normal angle
      return 1;
    }
    else if (x < 0 && y >= 0) { //180-angle
      return 2;
    }
    else if (x < 0 && y < 0) { //180+angle
      return 3;
    }
    else { //360-angle
      return 4;
    }
  }
}
