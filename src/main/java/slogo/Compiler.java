package slogo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Stack;
import slogo.CompilerExceptions.CompilerException;
import slogo.CompilerExceptions.NotAValueException;
import slogo.InstructionClasses.Instruction;

public class Compiler {

  public static final String DELIMITER = "\\s+";
  public static final String INSTRUCTION_PACKAGE = "slogo.InstructionClasses.";
  public static final String ERROR_RESOURCE_PACKAGE = "testfx.Error.";

  private static ResourceBundle inputToMethodBundle = ResourceBundle.getBundle("slogo.languages.SyntaxMethods");
  private PatternParser syntaxParser;
  private PatternParser languageParser;
  private Stack<Instruction> commandStack = new Stack<Instruction>();
  private Stack<Instruction> valueStack = new Stack<Instruction>();
  private Queue<String> userInputQueue = new LinkedList<String>();
  private Queue<Instruction> finalInstructionQueue = new LinkedList<Instruction>();
  private Map<String, Instruction> variablesMap = new HashMap<String, Instruction>(); //change out command for variable instead
  private ResourceBundle myErrorBundle;


  public Compiler(String language) {
    myErrorBundle = ResourceBundle.getBundle(ERROR_RESOURCE_PACKAGE+"English");
    syntaxParser = new PatternParser();
    languageParser = new PatternParser();
    syntaxParser.addPatterns("Syntax");
    languageParser.addPatterns(language);
  }

  public Queue<Instruction> getCommands(String userInput)
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, CompilerException {

    finalInstructionQueue.clear();
    makeUserInputStack(userInput);

    while(!userInputQueue.isEmpty()) {
      String currString = userInputQueue.peek();
      String currStringType = syntaxParser.getSymbol(currString);
      /*
      if(currStringType.equals("Constant")) {
        String errorMessage = myErrorBundle.get()
        throw new CompilerException(myErrorBundle.get())
      }
      */
      String methodName = inputToMethodBundle.getString(currStringType);
      Method inputMethod = this.getClass().getDeclaredMethod(methodName, null);
      inputMethod.setAccessible(true);
      Object returnValue = inputMethod.invoke(this);
    }
    finishCmdStack();
    return finalInstructionQueue;
  }

  private void commandMethod()
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, CompilerException {
    String cmdString = userInputQueue.poll();
    String translatedCmd = languageParser.getSymbol(cmdString);
    Class<?> currCmdClass = Class.forName(INSTRUCTION_PACKAGE + translatedCmd);
    Constructor<?> cmdConstructor = currCmdClass.getConstructor();
    Instruction currCmd = (Instruction) cmdConstructor.newInstance();

    finalInstructionQueue.offer(currCmd);
    for(int i = 0; i<currCmd.getNumParameters(); i++) {
      if(userInputQueue.size() == 0) {
        throw new CompilerException(myErrorBundle.getString("numParamError"), cmdString, currCmd.getNumParameters());
      }

      try{
        constantMethod();
      }
      catch (Exception NotAValueException) {
        commandStack.push(currCmd);
        return;
      }
    }
    currCmd.setParameters(valueStack);
  }

  private void constantMethod()
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

  private void commentMethod() {
    userInputQueue.poll();
  }

  private void finishCmdStack() {
    while(!commandStack.isEmpty()) {
      Instruction currCmd = commandStack.pop();
      currCmd.setParameters(valueStack);
    }
  }

  private void makeUserInputStack(String userInput) {
    for(String line : userInput.split("\n")) {
      if(!syntaxParser.getSymbol(line).equals("Comment")) { // can't have space after newline after comment
        for (String token : line.split(DELIMITER)) {
          userInputQueue.offer(token);
        }
      }
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
