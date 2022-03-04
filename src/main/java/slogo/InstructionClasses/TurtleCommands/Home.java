package slogo.InstructionClasses.TurtleCommands;

import java.util.function.BiFunction;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleModel;
import slogo.Model.TurtleRecord;

public class Home extends TurtleCommand{
  public static final int HOME_PARAM_NUM = 0;
  public static final double ORIGIN_X = 0;
  public static final double ORIGIN_Y = 0;
  public Home(TurtleCollection turtleModel) {
    super(HOME_PARAM_NUM, turtleModel);
  }

  @Override
  public BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda() {
    return (Instruction[] params, TurtleRecord myRecord) -> {
      return new TurtleRecord(ORIGIN_X, ORIGIN_Y, myRecord.myHeading(),
          myRecord.isPenDown(), myRecord.isShowing());
    };
  }

  @Override
  public double returnValue() {
    TurtleModel myCurrTurtle = getMyTurtles().getActiveTurtle();
    TurtleRecord currTurtleRecord = myCurrTurtle.getTurtleRecord();
    double currX = currTurtleRecord.myX();
    double currY = currTurtleRecord.myY();
    double distance = calcDistanceToXY(currX, currY, ORIGIN_X, ORIGIN_Y);
    return distance;
  }
}
