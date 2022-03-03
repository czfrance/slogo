package slogo.Model;

import java.util.ArrayList;
import java.util.Collections;
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
    createdTurtleMap.put(0, new TurtleModel());
    activeTurtleID = 0;
    activeTurtleList = new ArrayList<Integer>();
    activeTurtleList.add(activeTurtleID);
  }

  public boolean runInsn(Instruction[] insnParameters, BiFunction<Instruction[], TurtleRecord, TurtleRecord> function) {
      TurtleModel currTurtleModel = createdTurtleMap.get(activeTurtleID);
      currTurtleModel.runInsn(insnParameters, function);
      //activeTurtleID
    return true;
  }

  public void setActiveTurtles(List<Integer> newActiveTurtles) {
    activeTurtleList.clear();
    for(int i: newActiveTurtles) {
      activeTurtleList.add(i);
    }
    activeTurtleID = activeTurtleList.get(0);
  }

  public TurtleModel getActiveTurtle() {
    return createdTurtleMap.get(activeTurtleID);
  }

  public Map<Integer, TurtleModel> getCreatedTurtleMap() {
    return Collections.unmodifiableMap(createdTurtleMap);
  }

}
