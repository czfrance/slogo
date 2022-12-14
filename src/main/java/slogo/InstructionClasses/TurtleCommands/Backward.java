package slogo.InstructionClasses.TurtleCommands;

import java.util.function.BiFunction;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleModel;
import slogo.Model.TurtleRecord;

public class Backward extends TurtleCommand {
  public static final int BACKWARD_NUM_PARAM = 1;

  public Backward(TurtleCollection turtleModel) {
    super(BACKWARD_NUM_PARAM, turtleModel);
  }

  @Override
  public double returnValue() {
    double myPixels = getMyParameters()[0].returnValue();
    return myPixels;
  }

  @Override
  public BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda() {
    return (Instruction[] params, TurtleRecord myRecord) -> {
      System.out.println("back");
      double currX = myRecord.myX();
      double currY = myRecord.myY();
      double currHeading = myRecord.myHeading();
      double pixels = params[0].returnValue();
      currX = currX - calcXchange(pixels, currHeading);
      currY = currY - calcYchange(pixels, currHeading);
      return new TurtleRecord(currX, currY, currHeading, myRecord.isPenDown(), myRecord.isShowing());
    };
  }

  @Override
  public String toString() {
    double myPixels = getMyParameters()[0].returnValue();
    return String.format("back %f\n", myPixels);
  }
}
