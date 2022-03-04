package slogo.InstructionClasses.TurtleCommands;

import java.util.function.BiFunction;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleRecord;

//copy of SetTowards

public class SetXY extends TurtleCommand{
  public static final int SET_XY_PARAM_NUM = 2;

  private double distance;

  public SetXY(TurtleCollection turtleModel) {
    super(SET_XY_PARAM_NUM, turtleModel);
  }

  @Override
  public BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda() {
    return (Instruction[] params, TurtleRecord myRecord) -> {
      System.out.println("setXY");
      distance = calcDistanceToXY(params[0].returnValue(), params[1].returnValue(), myRecord);
      double currX = params[0].returnValue();
      double currY = params[1].returnValue();

      return new TurtleRecord(currX, currY, myRecord.myHeading(), myRecord.isPenDown(), myRecord.isShowing());
    };
  }

  @Override
  public double returnValue() {
    return distance;
  }

  private double calcDistanceToXY(double x, double y, TurtleRecord myRecord) {
    double xDist = Math.abs(myRecord.myX() - x);
    double yDist = Math.abs(myRecord.myY() - y);

    return Math.sqrt(xDist*xDist + yDist*yDist);
  }
}
