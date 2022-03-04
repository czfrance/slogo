package slogo.InstructionClasses.TurtleCommands;

import java.util.function.BiFunction;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleRecord;

public class PenUp extends TurtleCommand {
  public static final int PEN_UP_PARAM_NUM = 0;
  public static final int PEN_UP = 0;

  public PenUp(TurtleCollection turtleModel) {
    super(PEN_UP_PARAM_NUM, turtleModel);
  }

  @Override
  public BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda() {
    return (Instruction[] params, TurtleRecord myRecord) -> {
      System.out.println("penUp");
      boolean penIsDown = false;

      return new TurtleRecord(myRecord.myX(), myRecord.myY(), myRecord.myHeading(), penIsDown, myRecord.isShowing());
    };
  }

  @Override
  public double returnValue() {
    return PEN_UP;
  }

}
