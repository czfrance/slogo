package slogo.InstructionClasses;

import java.util.Deque;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;
import slogo.Model.TurtleModel;
import slogo.Model.TurtleRecord;

public class InsnList extends Instruction {

  Map<String, Variable> myVarMap;
  public InsnList(Deque<Instruction> instructionQueue, Map<String, Variable> varMap, TurtleModel model) {
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
      retString.append(getMyParameters()[i].toString());
    }
    return retString.toString();
  }
}
