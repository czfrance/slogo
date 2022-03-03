package slogo.InstructionClasses.TurtleCommands;

import java.util.Stack;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;

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

  protected double calcXchange(double pixels, double heading) {
    return pixels * Math.cos(Math.toRadians(heading));
  }

  protected double calcYchange(double pixels, double heading) {
    return pixels * Math.sin(Math.toRadians(heading));
  }

}
