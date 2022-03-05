package slogo.Model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.function.BiFunction;

import javafx.scene.layout.Region;
import slogo.InstructionClasses.Instruction;

public class TurtleModel extends Region {

  public static final boolean DEFAULT_PEN_DOWN = true;
  public static final boolean DEFAULT_SHOWING = true;
  public static final double[] DEFAULT_START = new double[]{0, 0};
  public static final int DEFAULT_HEADING = 90;

  private TurtleRecord myRecord;
  //assumption: facing right = 0 degrees, increases clockwise

  public TurtleModel() {
    myRecord = new TurtleRecord(DEFAULT_START[0], DEFAULT_START[1], DEFAULT_HEADING, DEFAULT_PEN_DOWN, DEFAULT_SHOWING);
  }

  public TurtleModel(double startX, double startY, double startHeading) {
    myRecord = new TurtleRecord(startX, startY, startHeading, DEFAULT_PEN_DOWN, DEFAULT_SHOWING);
  }

  public void runInsn(Instruction[] insnParameters, BiFunction<Instruction[], TurtleRecord, TurtleRecord> function) {
    myRecord = function.apply(insnParameters, myRecord);
  }

  public double[] getNextPos() {
    return new double[]{myRecord.myX(), myRecord.myY()};
  }

  //fix and get rid of
  public double getHeading() {
    return myRecord.myHeading();
  }

  public TurtleRecord getTurtleRecord() {
    return myRecord;
  }

}
