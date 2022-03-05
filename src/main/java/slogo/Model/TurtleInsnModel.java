package slogo.Model;

import java.lang.reflect.InvocationTargetException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import slogo.Compiler;
import slogo.InstructionClasses.Instruction;

public class TurtleInsnModel {
  private TurtleCollection myTurtleModel;
  private Compiler myCompiler;
  private Deque<Instruction> myInsnDeque = new LinkedList<Instruction>();
  private Deque<Instruction> myInsnDequeRunCopy = new LinkedList<Instruction>(myInsnDeque);
  public TurtleInsnModel(TurtleCollection model, String language) {
    myTurtleModel = model;
    myCompiler = new Compiler(language, myTurtleModel);
  }

  public void addUserInput(String userInput)
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
      myInsnDeque.addAll(myCompiler.getCommands(userInput));
      myInsnDequeRunCopy = new LinkedList<Instruction>(myInsnDeque);
      myTurtleModel.resetNumActiveTurtles();
  }

  public Optional<Object> runNextInsn() {
    if(!myInsnDequeRunCopy.isEmpty()) {
      Instruction nextInsn = myInsnDequeRunCopy.pop();
      System.out.println(nextInsn.toString());
      nextInsn.run();
      if(!nextInsn.getIsDone()) {
        //myInsnDequeRunCopy.addFirst(nextInsn);
        myInsnDequeRunCopy.addFirst(nextInsn);
      }
      return Optional.of(nextInsn.frontEndReturnValue());
    }
    return Optional.empty();
  }

  //make this return a immutable hashmap
  public Map<Integer, TurtleModel> getCreatedTurtleMap() {
    return myTurtleModel.getCreatedTurtleMap();
  }

  public List<Integer> getActiveIDList() {
    return myTurtleModel.getActiveTurtleIDList();
  }

}
