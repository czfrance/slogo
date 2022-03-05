package slogo.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import slogo.InstructionClasses.Instruction;

public class TurtleCollection {
  public static final int DEFAULT_INITIAL_TURTLE_ID = 1;

  private Map<Integer, TurtleModel> createdTurtleMap;
  private int activeTurtleID;
  private List<Integer> activeTurtleList;
  private List<Integer> tellTurtleList;
  private int numActiveTurtlesRun;

  public TurtleCollection() {
    createdTurtleMap = new HashMap<Integer, TurtleModel>();
    createdTurtleMap.put(DEFAULT_INITIAL_TURTLE_ID, new TurtleModel());
    activeTurtleID = DEFAULT_INITIAL_TURTLE_ID;
    activeTurtleList = new ArrayList<Integer>();
    tellTurtleList = new ArrayList<Integer>();
    activeTurtleList.add(activeTurtleID);
    numActiveTurtlesRun = 0;
  }

  public boolean runInsnOnTurtles(Instruction[] insnParameters, BiFunction<Instruction[], TurtleRecord, TurtleRecord> function) {
    System.out.println("Activeturtlelist: " + activeTurtleList);
    System.out.println("TellTurtleList: " + tellTurtleList);
    for(int i: activeTurtleList) {

      TurtleModel currTurtleModel = createdTurtleMap.get(i);
      currTurtleModel.runInsn(insnParameters, function);
    }

    return true;
  }

  public TurtleModel getActiveTurtle() {
    return createdTurtleMap.get(activeTurtleID);
  }

  protected List<Integer> getActiveTurtleIDList() {
    return activeTurtleList;
  }

  public int getActiveTurtleID() {
    return activeTurtleID;
  }

  //make immutable
  public Map<Integer, TurtleModel> getCreatedTurtleMap() {
    return Collections.unmodifiableMap(createdTurtleMap);
  }

  protected void resetNumActiveTurtles() {
    numActiveTurtlesRun = 0;
  }

  public int getNumActiveTurtlesRun() {
    return numActiveTurtlesRun;
  }

  public int getTotalActiveTurtles() {
    return getCreatedTurtleMap().size();
  }

  public void setActiveWithCond(Instruction conditional) {
    activeTurtleList.clear();
    for(int id: createdTurtleMap.keySet()) {
      activeTurtleID = id;
      System.out.printf("ID: %d conditional %f\n", id, conditional.returnValue());
      if(Math.abs(conditional.returnValue())>0.001) {
        System.out.println("turtleadded: " + id);
        activeTurtleList.add(id);
      }
    }
  }

  public void clearActiveTurtles() {
    activeTurtleList.clear();
    activeTurtleList.add(DEFAULT_INITIAL_TURTLE_ID);
  }

  public void clearTellTurtles() {
    tellTurtleList.clear();
  }

  public void addActiveTurtle(int id) {
    if(!createdTurtleMap.containsKey(id)) {
      createdTurtleMap.put(id, new TurtleModel());
    }
    activeTurtleList.add(id);
  }

  public void resetActiveToTellTurtles() {
    activeTurtleList.clear();
    activeTurtleList = new ArrayList<>(tellTurtleList);
  }

  public void addTellTurtle(int id) {
    if(!createdTurtleMap.containsKey(id)) {
      createdTurtleMap.put(id, new TurtleModel());
    }
    tellTurtleList.add(id);
  }
}
