package slogo.InstructionClasses.TurtleCommands;

import java.util.function.BiFunction;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleModel;
import slogo.Model.TurtleRecord;

public class Forward extends Instruction {
  public static final int FORWARD_PARAM_NUM = 1;

  public Forward(TurtleModel turtleModel) {
    super(FORWARD_PARAM_NUM, turtleModel);
  }

  /*
  @Override
  public void setParameters(Stack<Instruction> valueStack) {
    Instruction currParam = valueStack.pop();
    /*
    if(!isValueType("Constant", currParam)) {
      throw BadArgumentException;
    }

    getMyParameters()[0] = currParam;
    valueStack.push(this);
  }
  */

  @Override
  public double returnValue() {
    double myPixels = getMyParameters()[0].returnValue();
    return myPixels;
  }

//  @Override
//  public double frontEndReturnValue() {
//    return returnValue();
//  }

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

  private double calcXchange(double pixels, double heading) {
    return pixels * Math.cos(Math.toRadians(heading));
  }

  private double calcYchange(double pixels, double heading) {
    return pixels * Math.sin(Math.toRadians(heading));
  }

  @Override
  public String toString() {
    double myPixels = getMyParameters()[0].returnValue();
    return String.format("forward %f\n", myPixels);
  }
}
