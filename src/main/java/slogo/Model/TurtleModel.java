package slogo.Model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TurtleModel {
  InstructionModel insnModel;
  double x;
  double y;
  //assumption: facing right = 0 degrees, increases clockwise
  int heading;

  public TurtleModel(double startX, double startY, int startHeading) {
    insnModel = new InstructionModel();
    x = startX;
    y = startY;
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
    x = x + calcXchange(pixels);
    y = y + calcYchange(pixels);
    return pixels;
  }

  private int back(int[] params) {
    System.out.println("back");
    int pixels = params[0];
    x = x - calcXchange(pixels);
    y = y - calcYchange(pixels);
    return pixels;
  }

//  private int right(int[] params) {
//    System.out.println("right");
//    int degrees = params[0];
//    heading = heading;
//    return degrees;
//  }
//
//  private double checkHeading(int tempHeading) {
//    double newHeading = heading
//  }

  private double calcXchange(int pixels) {
    return pixels * Math.cos(Math.toRadians(heading));
  }

  private double calcYchange(int pixels) {
    return pixels * Math.sin(Math.toRadians(heading));
  }

  public double[] getNextPos() {
    return new double[]{x, y};
  }

  public double getHeading() {
    return heading;
  }
}
