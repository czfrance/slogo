package slogo.Model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

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

  public Object runNextInsn()
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    String[] insn = insnModel.getNextInsn().split(" ");
    Method m = this.getClass().getDeclaredMethod(insn[0]);
    return m.invoke(this, Arrays.copyOfRange(insn, 1, insn.length));
  }

  public void addInsn(String instruction) {
    insnModel.addInsn(instruction);
  }

  private int forward(int pixels) {
    x = x + calcXchange(pixels);
    y = y + calcYchange(pixels);
    return pixels;
  }

  private double calcXchange(int pixels) {
    return pixels * Math.cos(heading);
  }

  private double calcYchange(int pixels) {
    return pixels * Math.sin(heading);
  }
}
