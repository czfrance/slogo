package slogo.InstructionClasses.TurtleCommands;

import java.util.function.BiFunction;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleRecord;

public class ShowTurtle extends TurtleCommand{
  public static final int SHOW_TURTLE_PARAM_NUM = 0;
  public static final int SHOWING = 1;


  public ShowTurtle(TurtleCollection turtleModel) {
    super(SHOW_TURTLE_PARAM_NUM, turtleModel);
  }

  @Override
  public BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda() {
    return (Instruction[] params, TurtleRecord myRecord) -> {
      System.out.println("showTurtle");
      boolean isShowing = true;

      return new TurtleRecord(myRecord.myX(), myRecord.myY(), myRecord.myHeading(), myRecord.isPenDown(), isShowing);
    };
  }

  @Override
  public double returnValue() {
    return SHOWING;
  }
}
