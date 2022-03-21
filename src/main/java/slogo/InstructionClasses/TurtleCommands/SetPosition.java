package slogo.InstructionClasses.TurtleCommands;

import java.util.function.BiFunction;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleModel;
import slogo.Model.TurtleRecord;

public class SetPosition extends TurtleCommand{
  public static final int SET_POSITION_PARAM_NUM = 2;
  double distance;


  public SetPosition(TurtleCollection turtleModel) {
    super(SET_POSITION_PARAM_NUM, turtleModel);
  }

  @Override
  public BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda() {
    return (Instruction[] params, TurtleRecord myRecord) -> {
      double currX = myRecord.myX();
      double currY = myRecord.myY();
      distance = calcDistanceToXY(currX, currY, params[0].returnValue(), params[1].returnValue());
      double newX = params[0].returnValue();
      double newY=  params[1].returnValue();
      return new TurtleRecord(newX, newY, myRecord.myHeading(), myRecord.isPenDown(), myRecord.isShowing());

    };

  }

  @Override
  public double returnValue() {
//    TurtleModel myCurrTurtle = getMyTurtles().getActiveTurtle();
//    TurtleRecord currTurtleRecord = myCurrTurtle.getTurtleRecord();
//    double currX = currTurtleRecord.myX();
//    double currY = currTurtleRecord.myY();
//    double distance = calcDistanceToXY(currX, currY, getMyParameters()[0].returnValue(), getMyParameters()[1].returnValue());
//    System.out.println("DISTANCEE" + distance);
    return distance;
  }
}
