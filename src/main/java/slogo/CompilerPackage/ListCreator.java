package slogo.CompilerPackage;

import java.lang.reflect.InvocationTargetException;
import java.util.Deque;
import java.util.Map;
import java.util.Queue;
import java.util.ResourceBundle;
import slogo.CompilerExceptions.CompilerException;
import slogo.InstructionClasses.InsnList;
import slogo.InstructionClasses.Instruction;
import slogo.InstructionClasses.UserInstruction;
import slogo.InstructionClasses.Variable;
import slogo.Model.TurtleCollection;
import slogo.PatternParser;

public class ListCreator {
  private ResourceBundle myErrorBundle;
  private PatternParser syntaxParser;
  private Map<String, Variable> myVariablesMap;
  private Map<String, UserInstruction> myUserInstructionMap;
  private TurtleCollection myTurtles;

  public ListCreator(Map<String, Variable> variablesMap, Map<String, UserInstruction> userInstructionMap, TurtleCollection turtles) {
    syntaxParser = new PatternParser();
    syntaxParser.addPatterns("Syntax");
    myVariablesMap = variablesMap;
    myUserInstructionMap = userInstructionMap;
  }

  public InsnList createList(Queue<String> userInputQueue, Compiler parent)
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    Compiler listCompiler = new Compiler(parent);
    userInputQueue.poll(); //get rid of opening bracket
    StringBuilder listString = new StringBuilder();
    int listCount = 0;
    String nextUserSyntax = syntaxParser.getSymbol(userInputQueue.peek());
    while(!(nextUserSyntax.equals("ListEnd") && listCount == 0)) {
      if(nextUserSyntax.equals("ListStart")) {
        listCount++;
      }
      if(nextUserSyntax.equals("ListEnd")) {
        listCount--;
      }
      String stringAdded = userInputQueue.poll();
      if(stringAdded == null) throw new CompilerException(myErrorBundle.getString("noListEnd")); //have to add this error message still

      listString.append(stringAdded + " ");
      nextUserSyntax = syntaxParser.getSymbol(userInputQueue.peek());
    }
    userInputQueue.poll(); //get rid of ending bracket
    Deque<Instruction> listQueue = listCompiler.getCommands(listString.toString());
    InsnList listParam = new InsnList(listQueue, listCompiler.getVariableMap(), myTurtles);
    myVariablesMap.putAll(listCompiler.getVariableMap());
    myUserInstructionMap.putAll(listCompiler.getUserInstructionMap());
    return listParam;
  }
}
