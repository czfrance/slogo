package slogo.Model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TurtleModel {
  public static final int PEN_DOWN = 1;
  public static final int PEN_UP = 0;
  public static final int SHOWING = 1;
  public static final int HIDDEN = 0;

  InstructionModel insnModel;
  double myX;
  double myY;
  //assumption: facing right = 0 degrees, increases clockwise
  double heading;
  boolean penIsDown;
  boolean isShowing;

  public TurtleModel(double startX, double startY, int startHeading) {
    insnModel = new InstructionModel();
    myX = startX;
    myY = startY;
    heading = startHeading;
  }

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
//  todo: implement the rest of the functions
  private int right(int[] params) {
    System.out.println("right");
    int degrees = params[0];
    heading = checkHeading(heading - degrees);
    return degrees;
  }

  private int left(int[] params) {
    System.out.println("left");
    int degrees = params[0];
    heading = checkHeading(heading + degrees);
    return degrees;
  }

  private int setHeading(int[] params) {
    double oldHeading = heading;
    heading = checkHeading(params[0]);
    return (int)Math.abs(oldHeading - heading);
  }

  private int towards(int[] params) {
    double oldHeading = heading;
    heading = checkHeading(params[0]);
    return (int)Math.abs(oldHeading - heading);
  }

  private int setXY(int[] params) {
    myX = params[0];
    myY = params[1];
    return calcDistanceToXY(params[0], params[1]);
  }

  private int penDown(int[] params) {
    penIsDown = true;
    return PEN_DOWN;
  }

  private int penUp(int[] params) {
    penIsDown = false;
    return PEN_UP;
  }

  private int showTurtle(int[] params) {
    isShowing = true;
    return SHOWING;
  }

  private int hideTurtle(int[] params) {
    isShowing = false;
    return HIDDEN;
  }

  private double calcAbsHeading(int x, int y) {
    double angleToX = calcAngleToX(x, y);
  }

  private double calcAngleToX(int x, int y) {
    double xDist = Math.abs(myX - x);
    double yDist = Math.abs(myY - y);

    return Math.atan(xDist/yDist);
  }

//  private int findQuadrant(int x, int y) {
//    if (x > 0 && y > 0) {
//      return 1;
//    }
//    else if (x > 0 && y > 0) {
//      return 1;
//    }
//    if (x > 0 && y > 0) {
//      return 1;
//    }
//    if (x > 0 && y > 0) {
//      return 1;
//    }
//  }

  private int calcDistanceToXY(int x, int y) {
    double xDist = Math.abs(myX - x);
    double yDist = Math.abs(myY - y);

    return (int)Math.sqrt(xDist*xDist + yDist*yDist);
  }

//  private double calcDegreestoXY(int x, int y) {
//
//  }

  private double checkHeading(double tempHeading) {
    if (tempHeading < 0) {
      return 360 + tempHeading;
    }
    else if (tempHeading > 360) {
      return 360 - tempHeading;
    }
    return tempHeading;
  }

  private double calcXchange(int pixels) {
    return pixels * Math.cos(Math.toRadians(heading));
  }

  private double calcYchange(int pixels) {
    return pixels * Math.sin(Math.toRadians(heading));
  }

  public double[] getNextPos() {
    return new double[]{myX, myY};
  }

  public double getHeading() {
    return heading;
  }
}
