package slogo.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import slogo.InstructionClasses.Instruction;

public class TurtleCollection {
  private Map<Integer, TurtleModel> createdTurtleMap;
  private int activeTurtleID;
  private List<Integer> activeTurtleList;

  public TurtleCollection() {
    createdTurtleMap = new HashMap<Integer, TurtleModel>();
    createdTurtleMap.put(0, new TurtleModel(0,0,0));
    activeTurtleID = 0;
    activeTurtleList = new ArrayList<Integer>();
    activeTurtleList.add(activeTurtleID);
  }

  public void runInsn(Instruction[] insnParameters, BiFunction<Instruction[], TurtleRecord, TurtleRecord> function) {
    for(int i: activeTurtleList) {
      activeTurtleID = i;
      TurtleModel currTurtleModel = createdTurtleMap.get(activeTurtleID);
      currTurtleModel.runInsn(insnParameters, function);
    }
  }

  public void setActiveTurtles(List<Integer> newActiveTurtles) {
    for(int i: newActiveTurtles) {

    }
  }

  public TurtleModel getActiveTurtle() {
    return createdTurtleMap.get(activeTurtleID);
  }

}
