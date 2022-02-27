package slogo.InstructionClasses.TurtleCommands;

import slogo.InstructionClasses.Instruction;

public class Backward extends Instruction {
  public static final int BACKWARD_NUM_PARAM = 1;

  public Backward() {
    super(BACKWARD_NUM_PARAM);
  }

  @Override
  public double returnValue() {
    double myPixels = getMyParameters()[0].returnValue();
    return myPixels;
  }

  @Override
  public void run() {

  }

  @Override
  public String toString() {
    double myPixels = getMyParameters()[0].returnValue();
    return String.format("back %f\n", myPixels);
  }
}
