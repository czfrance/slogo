package slogo.InstructionClasses.TurtleCommands;

import java.util.function.BiFunction;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleRecord;

public class PenDown extends TurtleCommand{

  public static final int PEN_DOWN_PARAM_NUM = 0;

  public PenDown(TurtleCollection turtleModel) {
    super(PEN_DOWN_PARAM_NUM, turtleModel);
  }

  @Override
  public BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda() {
    return (Instruction[] params, TurtleRecord myRecord) -> {

      return new TurtleRecord(myRecord.myX(), myRecord.myY(), myRecord.myHeading(), true, myRecord.isShowing());

    };
  }

  @Override
  public double returnValue() {
    return 1;
  }
}
