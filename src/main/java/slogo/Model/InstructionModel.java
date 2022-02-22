package slogo.Model;

import java.io.LineNumberInputStream;
import java.util.LinkedList;
import java.util.Queue;

public class InstructionModel {
  Queue<String> insnQueue;

  public InstructionModel() {
    insnQueue = new LinkedList<>();
  }

  public void addInsn(String instruction) {
    insnQueue.add(instruction);
  }

  public String getNextInsn() {
    return insnQueue.poll();
  }

  public boolean hasNextInsn() {
    return !insnQueue.isEmpty();
  }

}
