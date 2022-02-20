package slogo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import slogo.CompilerExceptions.NotAValueException;
import slogo.InstructionClasses.Instruction;

public class Compiler {

  public static final String DELIMITER = "\\s+";
  public static final String INSTRUCTION_PACKAGE = "slogo.InstructionClasses.";

  private PatternParser syntaxParser = new PatternParser();
  private PatternParser languageParser = new PatternParser();
  private Stack<Instruction> commandStack = new Stack<Instruction>();
  private Stack<Instruction> valueStack = new Stack<Instruction>();
  private Queue<String> userInputQueue = new LinkedList<String>();
  private Queue<Instruction> finalInstructionQueue = new LinkedList<Instruction>();
  private Map<String, Instruction> variablesMap = new HashMap<String, Instruction>(); //change out command for variable instead

  public Compiler(String language) {
    syntaxParser.addPatterns("Syntax");
    languageParser.addPatterns(language);
  }

  public Queue<Instruction> getCommands(String userInput)
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NotAValueException {

    finalInstructionQueue.clear();
    makeUserInputStack(userInput);

    while(!userInputQueue.isEmpty()) {
      String currString = userInputQueue.peek();
      String currStringType = syntaxParser.getSymbol(currString);
      if(currStringType.equals("Comment")) {
        userInputQueue.poll();
        continue;
      }
      if(currStringType.equals("UserCommand")) {
        checkAndPushCommand();
      }
      //do this in initializeValue instead
      /*
      else if(currStringType.equals("Variable")) {
        initializeVariable();
      }
       */
      else {
        initializeAndPushValue();
      }
    }
    finishCmdStack();
    return finalInstructionQueue;
  }

  private void checkAndPushCommand()
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    String cmdString = userInputQueue.poll();
    String translatedCmd = languageParser.getSymbol(cmdString);
    Class<?> currCmdClass = Class.forName(INSTRUCTION_PACKAGE + translatedCmd);
    Constructor<?> cmdConstructor = currCmdClass.getConstructor();
    Instruction currCmd = (Instruction) cmdConstructor.newInstance();

    finalInstructionQueue.offer(currCmd);
    for(int i = 0; i<currCmd.getNumParameters(); i++) {
      //implement exceptions later
      /*
      if(userInputQueue.size() == 0) {
        throw WrongNumParameterException();
      }

       */
      try{
        initializeAndPushValue();
      }
      catch (Exception NotAValueException) {
        commandStack.push(currCmd);
        return;
      }
    }
    currCmd.setParameters(valueStack);
  }

  private void initializeAndPushValue()
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NotAValueException {
    String valString = userInputQueue.peek();
    String valType = syntaxParser.getSymbol(valString);
    if(!(valType.equals("Constant") || valType.equals("Variable"))) {
      throw new NotAValueException();
    }
    valString = userInputQueue.poll();
    Class<?> currValClass = Class.forName(INSTRUCTION_PACKAGE + valType);
    Constructor<?> valConstructor = currValClass.getConstructor(String.class);
    Instruction currVal = (Instruction) valConstructor.newInstance(valString);

    valueStack.push(currVal);
  }

  private void finishCmdStack() {
    while(!commandStack.isEmpty()) {
      Instruction currCmd = commandStack.pop();
      currCmd.setParameters(valueStack);
    }
  }

  private void makeUserInputStack(String userInput) {
    for (String token : userInput.split(DELIMITER)) {
      userInputQueue.offer(token);
    }
  }

  private Queue<String> getStringQueue() {
    Queue<String> stringQueue = new LinkedList<String>();
    while(!finalInstructionQueue.isEmpty()) {
      Instruction currInstruction = finalInstructionQueue.poll();
      stringQueue.offer(currInstruction.toString());
    }
    return stringQueue;
  }

  @Override
  public String toString() {
    StringBuilder returnString = new StringBuilder();
    while(!finalInstructionQueue.isEmpty()) {
      String currString = finalInstructionQueue.poll().toString();

      returnString.append(currString);
    }
    return returnString.toString();
  }
}
