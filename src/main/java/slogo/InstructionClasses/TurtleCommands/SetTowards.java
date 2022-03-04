package slogo.InstructionClasses.TurtleCommands;

import java.util.function.BiFunction;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleModel;
import slogo.Model.TurtleRecord;

public class SetTowards extends TurtleCommand{
  public static final int SET_TOWARDS_PARAM_NUM = 2;

  public SetTowards(TurtleCollection turtleModel) {
    super(SET_TOWARDS_PARAM_NUM, turtleModel);
  }

  @Override
  public double returnValue() {
    TurtleModel myCurrTurtle = getMyTurtles().getActiveTurtle();
    TurtleRecord currTurtleRecord = myCurrTurtle.getTurtleRecord();
    Double heading = currTurtleRecord.myHeading();
    heading = checkHeading(heading);
    double oldHeading = heading;
    double newHeading = calcAbsHeading(currTurtleRecord.myX(), currTurtleRecord.myY(), heading, getMyParameters()[0].returnValue(), getMyParameters()[1].returnValue());
    //heading = checkHeading(newHeading);
    return newHeading - oldHeading;
  }

  @Override
  public BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda() {
    return (Instruction[] params, TurtleRecord myRecord) -> {
      double heading = myRecord.myHeading();
      double currX = myRecord.myX();
      double currY = myRecord.myY();
      heading = checkHeading(heading);
      double newHeading = calcAbsHeading(currX, currY, heading, params[0].returnValue(), params[1].returnValue());

      return new TurtleRecord(myRecord.myX(), myRecord.myY(), newHeading, myRecord.isPenDown(), myRecord.isShowing());
    };
  }
}
