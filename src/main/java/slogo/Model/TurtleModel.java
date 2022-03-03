package slogo.Model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.function.BiFunction;
import slogo.InstructionClasses.Instruction;

public class TurtleModel {
  public static final int PEN_DOWN = 1;
  public static final int PEN_UP = 0;
  public static final int SHOWING = 1;
  public static final int HIDDEN = 0;

  private InstructionModel insnModel;
  private TurtleRecord myRecord;
  private double myX;
  private double myY;
  //assumption: facing right = 0 degrees, increases clockwise
  private double heading;
  private boolean penIsDown;
  private boolean isShowing;

  public TurtleModel(double startX, double startY, double startHeading) {
    insnModel = new InstructionModel();
    myX = startX;
    myY = startY;
    heading = startHeading;
    myRecord = new TurtleRecord(startX, startY, startHeading, true, true);
    penIsDown = true;
    isShowing = true;
  }

  public void runInsn(Instruction[] insnParameters, BiFunction<Instruction[], TurtleRecord, TurtleRecord> function) {
    myRecord = function.apply(insnParameters, myRecord);
  }

  public double[] getNextPos() {
    return new double[]{myRecord.myX(), myRecord.myY()};
  }

  public double getHeading() {
    return myRecord.myHeading();
  }

//  public double[] getNextPos() {
//    return new double[]{myX, myY};
//  }
//
//  public double getHeading() {
//    return heading;
//  }

  public Optional<Object> runNextInsn()
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    if (insnModel.hasNextInsn()) {
      String[] insn = insnModel.getNextInsn().split(" ");
      int[] params = makeInts(insn);
      Method m = this.getClass().getDeclaredMethod(insn[0], params.getClass());
      return Optional.of(m.invoke(this, params));
    }
    return Optional.empty();
  }

  public void addInsn(String instruction) {
    insnModel.addInsn(instruction);
  }

  public boolean penIsDown() {
    return penIsDown;
  }

  public TurtleRecord getTurtleRecord() {
    return myRecord;
  }

  private int[] makeInts(String[] insn) {
    int[] params = new int[insn.length-1];
    for (int i = 1; i < insn.length; i++) {
      params[i-1] = Integer.parseInt(insn[i]);
    }
    return params;
  }

  private int forward(int[] params) {
    System.out.println("forward");
    int pixels = params[0];
    myX = myX + calcXchange(pixels);
    myY = myY + calcYchange(pixels);
    return pixels;
  }

  private int back(int[] params) {
    System.out.println("back");
    int pixels = params[0];
    myX = myX - calcXchange(pixels);
    myY = myY - calcYchange(pixels);
    return pixels;
  }

  private int right(int[] params) {
    heading = checkHeading(heading);
    System.out.println("right");
    int degrees = params[0];
    heading = heading - degrees;
    return degrees;
  }

  private int left(int[] params) {
    heading = checkHeading(heading);
    System.out.println("left");
    int degrees = params[0];
    heading = heading + degrees;
    return -1*degrees;
  }

  private int setHeading(int[] params) {
    System.out.println("setHeading");
    heading = checkHeading(heading);
    double oldHeading = heading;
    heading = checkHeading(params[0]);
    return (int)(oldHeading - heading);
  }

  private int towards(int[] params) {
    System.out.println("towards");
    heading = checkHeading(heading);
    double oldHeading = heading;
    double newHeading = calcAbsHeading(params[0], params[1]);
    heading = newHeading;
    //heading = checkHeading(newHeading);
    return (int)(oldHeading - heading);
  }
  private int setXY(int[] params) {
    System.out.println("setXY");
    int distance = calcDistanceToXY(params[0], params[1]);
    myX = params[0];
    myY = params[1];
    return distance;
  }

  private int penDown(int[] params) {
    System.out.println("penDown");
    penIsDown = true;
    return PEN_DOWN;
  }

  private int penUp(int[] params) {
    System.out.println("penUp");
    penIsDown = false;
    return PEN_UP;
  }

  private int showTurtle(int[] params) {
    System.out.println("showTurtle");
    isShowing = true;
    return SHOWING;
  }

  private int hideTurtle(int[] params) {
    System.out.println("hideTurtle");
    isShowing = false;
    return HIDDEN;
  }

  private double calcAbsHeading(int x, int y) {
    if (x == myX && y == myY) {
      return heading;
    }

    double angleToX = calcAngleToX(x, y);
    int quadrant = findQuadrant(x, y);

    switch (quadrant) {
      case 1 -> {return angleToX;}
      case 2 -> {return 180-angleToX;}
      case 3 -> {return 180+angleToX;}
      default -> {return 360-angleToX;}
    }
  }

  private double calcAngleToX(int x, int y) {
    double xDist = Math.abs(myX - x);
    double yDist = Math.abs(myY - y);

    if (xDist == 0) {return 90;}

    return Math.toDegrees(Math.atan(yDist/xDist));
  }

  private int findQuadrant(int x, int y) {
    if (x >= 0 && y >= 0) { //normal angle
      return 1;
    }
    else if (x < 0 && y >= 0) { //180-angle
      return 2;
    }
    else if (x < 0 && y < 0) { //180+angle
      return 3;
    }
    else { //360-angle
      return 4;
    }
  }

  private int calcDistanceToXY(int x, int y) {
    double xDist = Math.abs(myX - x);
    double yDist = Math.abs(myY - y);

    return (int)Math.sqrt(xDist*xDist + yDist*yDist);
  }

  private double checkHeading(double tempHeading) {
    if (tempHeading < 0) {
      return 360 + tempHeading;
    }
    else if (tempHeading > 360) {
      return tempHeading - 360;
    }
    return tempHeading;
  }

  private double calcXchange(int pixels) {
    return pixels * Math.cos(Math.toRadians(heading));
  }

  private double calcYchange(int pixels) {
    return pixels * Math.sin(Math.toRadians(heading));
  }


}
