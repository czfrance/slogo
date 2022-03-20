package slogo.Model;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import slogo.CompilerExceptions.CompilerException;
import slogo.CompilerPackage.Compiler;
import slogo.InstructionClasses.Instruction;

public class TurtleInsnModel {
  private TurtleCollection myTurtleModel;
  private Compiler myCompiler;
  private Deque<Instruction> myInsnDeque = new LinkedList<Instruction>();
  //private Deque<Instruction> myInsnDequeRunCopy = new LinkedList<Instruction>(myInsnDeque);
  public TurtleInsnModel(TurtleCollection model, String language) {
    myTurtleModel = model;
    myCompiler = new Compiler(language, myTurtleModel);
  }

  public void addUserInput(String userInput)
      throws CompilerException {
      myInsnDeque.addAll(myCompiler.getCommands(userInput));
      myTurtleModel.resetNumActiveTurtles();
  }

  public Optional<Object> runNextInsn() {
    if(!myInsnDeque.isEmpty()) {
      Instruction nextInsn = myInsnDeque.pop();
      System.out.println(nextInsn.toString());
      nextInsn.run();
      if(!nextInsn.getIsDone()) {
        //myInsnDequeRunCopy.addFirst(nextInsn);
        myInsnDeque.addFirst(nextInsn);
      }
      return Optional.of(nextInsn.frontEndReturnValue());
    }
    return Optional.empty();
  }

  //make this return a immutable hashmap
  public Map<Integer, TurtleModel> getCreatedTurtleMap() {
    return Collections.unmodifiableMap(myTurtleModel.getCreatedTurtleMap());
  }

  public Compiler getCompiler(){
    return myCompiler;
  }

  public List<Integer> getActiveIDList() {
    return myTurtleModel.getActiveTurtleIDList();
  }

}
