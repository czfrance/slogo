package slogo.InstructionClasses.TurtleCommands;

import java.util.function.BiFunction;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleRecord;

public class PenDown extends TurtleCommand {
  public static final int PEN_DOWN_PARAM_NUM = 0;
  public static final int PEN_DOWN = 1;

  public PenDown(TurtleCollection turtleModel) {
    super(PEN_DOWN_PARAM_NUM, turtleModel);
  }

  @Override
  public BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda() {
    return (Instruction[] params, TurtleRecord myRecord) -> {
      System.out.println("penDown");
      boolean penIsDown = true;

      return new TurtleRecord(myRecord.myX(), myRecord.myY(), myRecord.myHeading(), penIsDown, myRecord.isShowing());
    };
  }

  @Override
  public double returnValue() {
    return PEN_DOWN;
  }
}
