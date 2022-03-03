package slogo.InstructionClasses.TurtleCommands;

import java.util.function.BiFunction;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleModel;
import slogo.Model.TurtleRecord;

public class Forward extends TurtleCommand {
  public static final int FORWARD_PARAM_NUM = 1;

  public Forward(TurtleCollection turtleModel) {
    super(FORWARD_PARAM_NUM, turtleModel);
  }

  @Override
  public double returnValue() {
    double myPixels = getMyParameters()[0].returnValue();
    return myPixels;
  }

  @Override
  public BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda() {
    return (Instruction[] params, TurtleRecord myRecord) -> {

      double currX = myRecord.myX();
      double currY = myRecord.myY();
      double currHeading = myRecord.myHeading();
      double pixels = params[0].returnValue();
      currX = currX + calcXchange(pixels, currHeading);
      currY = currY + calcYchange(pixels, currHeading);
      System.out.printf("forward %s\n", pixels);
      return new TurtleRecord(currX, currY, currHeading, myRecord.isPenDown(), myRecord.isShowing());
    };
  }

  @Override
  public String toString() {
    double myPixels = getMyParameters()[0].returnValue();
    return String.format("forward %f\n", myPixels);
  }
}
