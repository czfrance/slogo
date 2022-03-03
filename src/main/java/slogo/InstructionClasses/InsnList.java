package slogo.InstructionClasses;

import java.util.Deque;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleModel;
import slogo.Model.TurtleRecord;

public class InsnList extends Instruction {

  private Map<String, Variable> myVarMap;

  private int currNumInstruction = 0;

  public InsnList(Deque<Instruction> instructionQueue, Map<String, Variable> varMap, TurtleCollection model) {
    super(instructionQueue.size(), model);
    queueToParam(instructionQueue);
    myVarMap = varMap;
  }

  @Override
  public void setParameters(Stack<Instruction> valueStack) {
    // Lists have parameters set in constructor
  }

  private void queueToParam(Deque<Instruction> instructionQueue) {
    int counter = 0;

    while(!instructionQueue.isEmpty()) {
      getMyParameters()[counter] = instructionQueue.poll();
      counter++;
    }
  }

  @Override
  public void run() {
    if(currNumInstruction<getNumParameters()) {
      Instruction currInstruction = getMyParameters()[currNumInstruction];
      currInstruction.run();
      if(currInstruction.getIsDone()) {
        currNumInstruction++;
      }
    }
    if(currNumInstruction>=getNumParameters()) {
      setIsDone(true);
    }
  }

  public Map<String, Variable> getVarMap() {
    return myVarMap;
  }

  @Override
  public double returnValue() {
    return getMyParameters()[getNumParameters()-1].returnValue();
  }

  @Override
  public BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda() {
    return (Instruction[] params, TurtleRecord myRecord) -> myRecord;
  }

  @Override
  public String toString() {
    StringBuilder retString = new StringBuilder();
    for(int i = 0; i<getNumParameters(); i++) {
      if(getMyParameters()[i]!=null) {
        retString.append(getMyParameters()[i].toString());
      }
    }
    return retString.toString();
  }
}
