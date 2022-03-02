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
import slogo.InstructionClasses.Variable;
import slogo.Model.TurtleModel;

public class Compiler {

  public static final String DELIMITER = "\\s+";
  public static final String INSTRUCTION_PACKAGE = "slogo.InstructionClasses.";
  public static final String ERROR_RESOURCE_PACKAGE = "testfx.Error.";
  public static final ResourceBundle inputToMethodBundle = ResourceBundle.getBundle("slogo.languages.SyntaxMethods");
  public static final ResourceBundle INSTRUCTION_TYPE_BUNDLE = ResourceBundle.getBundle("slogo.languages.InstructionType");

  private PatternParser syntaxParser;
  private PatternParser languageParser;
  private Stack<Instruction> commandStack = new Stack<Instruction>();
  private Stack<Instruction> valueStack = new Stack<Instruction>();
  private Queue<String> userInputQueue = new LinkedList<String>();
  private Queue<Instruction> finalInstructionQueue = new LinkedList<Instruction>();
  private Map<String, Variable> variablesMap = new HashMap<String, Variable>(); //change out command for variable instead
  private ResourceBundle myErrorBundle;
  private TurtleModel myModel;

  public Compiler(String language, TurtleModel turtleModel) {
    myErrorBundle = ResourceBundle.getBundle(ERROR_RESOURCE_PACKAGE+"English");
    syntaxParser = new PatternParser();
    languageParser = new PatternParser();
    syntaxParser.addPatterns("Syntax");
    languageParser.addPatterns(language);
    myModel = turtleModel;
  }

  public Queue<Instruction> getCommands(String userInput)
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, CompilerException {

    finalInstructionQueue.clear();
    makeUserInputStack(userInput);

    while(!userInputQueue.isEmpty()) {
      String currString = userInputQueue.peek();
      String currStringType = syntaxParser.getSymbol(currString);
      System.out.println(currString);
      if(languageParser.getSymbol(currString).equals("MakeVariable")) {
        makeVariable();
      }
      //if(languageParser.getSymbol(currString).equals("MakeUserInstruction")) makeUserInstruction();
      /*
      if(currStringType.equals("Constant")) {
        String errorMessage = myErrorBundle.get()
        throw new CompilerException(myErrorBundle.get())
      }
      */
      else {
        String methodName = inputToMethodBundle.getString(currStringType);
        Method inputMethod = this.getClass().getDeclaredMethod(methodName, null);
        inputMethod.setAccessible(true);
        Object returnValue = inputMethod.invoke(this);
      }
    }
    finishCmdStack();
    return finalInstructionQueue;
  }

  private void commandMethod()
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, CompilerException {
    String cmdString = userInputQueue.poll();
    String translatedCmd = languageParser.getSymbol(cmdString);
    if(translatedCmd.equals("MakeVariable")) {
      makeVariable();
    }
    Class<?> currCmdClass = Class.forName(INSTRUCTION_PACKAGE + INSTRUCTION_TYPE_BUNDLE.getString(translatedCmd) + "." + translatedCmd);
    Constructor<?> cmdConstructor = currCmdClass.getConstructor(new Class[]{TurtleModel.class});
    Instruction currCmd = (Instruction) cmdConstructor.newInstance(myModel);

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
    //make these into the reflection from resource bundle thing
    if(valType.equals("Variable")) {
      userInputQueue.poll();
      Variable varValue = variablesMap.get(valString);
      if(varValue == null) throw new CompilerException("Variable %d never initialized", varValue);
      valueStack.push(varValue);
      return;
    }
    if(valType.equals("ListStart") || valType.equals("ListEnd"))
    if(!(valType.equals("Constant") || valType.equals("Variable") || valType.equals("ListStart"))) {
      throw new NotAValueException();
    }
    valString = userInputQueue.poll();
    Class<?> currValClass = Class.forName(INSTRUCTION_PACKAGE + valType);
    Constructor<?> valConstructor = currValClass.getConstructor(String.class);
    Instruction currVal = (Instruction) valConstructor.newInstance(valString);

    valueStack.push(currVal);
  }

  private void makeVariable()
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NotAValueException {
    userInputQueue.poll(); //remove makeVar method call
    String name = userInputQueue.poll();
    Variable newVar = new Variable(name, myModel);
    variablesMap.put(name, newVar);
    finalInstructionQueue.offer(newVar);
    try{
      constantMethod();
    }
    catch (Exception NotAValueException) {
      commandStack.push(newVar);
      return;
    }
    newVar.setParameters(valueStack);
  }


  private void finishCmdStack() {
    while(!commandStack.isEmpty()) {
      Instruction currCmd = commandStack.pop();
      currCmd.setParameters(valueStack);
    }
  }

  private void makeUserInputStack(String userInput) {
    userInput.trim();
    for(String line : userInput.split("\n")) {
      line.trim();
      if(!syntaxParser.getSymbol(line).equals("Comment")) { // can't have space after newline after comment
        for (String token : line.split(DELIMITER)) {
          userInputQueue.offer(token);
        }
      }
    }
  }
  public Map getVariableMap(){
    return variablesMap;
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
