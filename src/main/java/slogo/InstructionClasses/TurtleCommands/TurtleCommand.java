package slogo.InstructionClasses.TurtleCommands;

import java.util.Stack;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleRecord;

public abstract class TurtleCommand extends Instruction {

  public TurtleCommand(int paramNum, TurtleCollection turtleModel) {
    super(paramNum, turtleModel);
  }

  @Override
  public abstract double returnValue();

  protected double checkHeading(double tempHeading) {
    if (tempHeading < 0) {
      return 360 + tempHeading;
    }
    else if (tempHeading > 360) {
      return 360 - tempHeading;
    }
    return tempHeading;
  }

  protected double calcXchange(double pixels, double currHeading) {
    return pixels * Math.cos(Math.toRadians(currHeading));
  }

  protected double calcYchange(double pixels, double currHeading) {
    return pixels * Math.sin(Math.toRadians(currHeading));
  }

  protected double calcAngleToX(double currX, double currY, double x, double y) {
    double xDist = Math.abs(currX - x);
    double yDist = Math.abs(currY - y);

    if (xDist == 0) {return 90;}

    return Math.toDegrees(Math.atan(yDist/xDist));
  }

  protected int findQuadrant(double x, double y) {
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

  protected double calcDistanceToXY(double currX, double currY, double x, double y) {
    double xDist = Math.abs(currX - x);
    double yDist = Math.abs(currY - y);

    return Math.sqrt(xDist*xDist + yDist*yDist);
  }

}
