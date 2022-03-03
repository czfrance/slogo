package slogo.Model;

import java.lang.reflect.InvocationTargetException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;
import slogo.Compiler;
import slogo.InstructionClasses.Instruction;

public class TurtleInsnModel {
  TurtleCollection myTurtleModel;
  Compiler myCompiler;
  Deque<Instruction> myInsnDeque = new LinkedList<Instruction>();
  public TurtleInsnModel(TurtleCollection model, String language) {
    myTurtleModel = model;
    myCompiler = new Compiler(language, myTurtleModel);
  }

  public void addUserInput(String userInput)
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    myInsnDeque.addAll(myCompiler.getCommands(userInput));
  }

  public Optional<Object> runNextInsn() {
    if(!myInsnDeque.isEmpty()) {
      Instruction nextInsn = myInsnDeque.pop();
      nextInsn.run();
      if(!nextInsn.getIsDone()) {
        myInsnDeque.addFirst(nextInsn);
      }
      return Optional.of(nextInsn.frontEndReturnValue());
    }
    return Optional.empty();
  }

  public TurtleModel getCurrTurtle() {
    return myTurtleModel.getActiveTurtle();
  }


}
