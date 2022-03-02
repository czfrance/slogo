package slogo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Stack;
import slogo.CompilerExceptions.CompilerException;
import slogo.CompilerExceptions.NotAValueException;
import slogo.InstructionClasses.Instruction;
import slogo.InstructionClasses.InsnList;
import slogo.InstructionClasses.UserInstruction;
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
  private Deque<Instruction> finalInstructionQueue = new LinkedList<Instruction>();
  private Map<String, Variable> variablesMap = new HashMap<String, Variable>(); //change out command for variable instead
  private Map<String, UserInstruction> userInstructionMap = new HashMap<String, UserInstruction>();
  private ResourceBundle myErrorBundle;
  private TurtleModel myModel;
  private String myLanguage;

  public Compiler(String language, TurtleModel turtleModel) {
    myLanguage = language;

    myErrorBundle = ResourceBundle.getBundle(ERROR_RESOURCE_PACKAGE+"English");
    syntaxParser = new PatternParser();
    languageParser = new PatternParser();
    syntaxParser.addPatterns("Syntax");
    languageParser.addPatterns(language);
    myModel = turtleModel;

  }

  public Compiler(Compiler parent) {
    variablesMap = parent.variablesMap;
    userInstructionMap = parent.userInstructionMap;
    myLanguage = parent.myLanguage;
    myErrorBundle = parent.myErrorBundle;
    syntaxParser = parent.syntaxParser;
    languageParser = parent.languageParser;
    myModel = parent.myModel;
  }

  public Deque<Instruction> getCommands(String userInput)
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, CompilerException {

    finalInstructionQueue.clear();
    makeUserInputStack(userInput);

    while(!userInputQueue.isEmpty()) {
      String currString = userInputQueue.peek();
      String currStringType = syntaxParser.getSymbol(currString);
      System.out.format("%s : %s\n" , currString, currStringType);
      if(languageParser.getSymbol(currString).equals("MakeVariable")) {
        makeVariable();
      }
      else if (languageParser.getSymbol(currString).equals("MakeUserInstruction")) {
        makeUserInstruction();
      }
      else if (currStringType.equals("Variable")) makeEmptyVariable();
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
    Instruction currCmd;
    if(translatedCmd.equals(PatternParser.NO_MATCH)) {
      System.out.println("made it to user cmd");
      currCmd = userInstructionMap.get(cmdString);
      if(currCmd == null) throw new CompilerException(myErrorBundle.getString("noSuchInstructionError")); //haven't made this error bundle yet
      currCmd = new UserInstruction(userInstructionMap.get(cmdString));
    }
    else {
      Class<?> currCmdClass = Class.forName(INSTRUCTION_PACKAGE + INSTRUCTION_TYPE_BUNDLE.getString(translatedCmd) + "." + translatedCmd);
      Constructor<?> cmdConstructor = currCmdClass.getConstructor(new Class[]{TurtleModel.class});
      currCmd = (Instruction) cmdConstructor.newInstance(myModel);
    }

    finalInstructionQueue.offer(currCmd);
    for(int i = 0; i<currCmd.getNumParameters(); i++) {
      if(userInputQueue.size() == 0) {
        throw new CompilerException(myErrorBundle.getString("numParamError"), cmdString, currCmd.getNumParameters());
      }

      try{
        constantMethod();
      }
      catch (NotAValueException notVal) {
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
    System.out.format("%s, %s\n", valString, valType);
    //make these into the reflection from resource bundle thing
    if(valType.equals("Variable")) {
      userInputQueue.poll();
      Variable varValue = variablesMap.get(valString);
      if(varValue == null) {
        System.out.println("bruh");
        throw new CompilerException("Variable %d never initialized", valString);
      }
      valueStack.push(varValue);
      return;
    }
    else if(valType.equals("ListStart") || valType.equals("ListEnd")) {
      InsnList listParam = createList();
      valueStack.push(listParam);
      return;
    }
    else if(!(valType.equals("Constant") || valType.equals("Variable") || valType.equals("ListStart"))) {
      throw new NotAValueException();
    }
    valString = userInputQueue.poll();
    Class<?> currValClass = Class.forName(INSTRUCTION_PACKAGE + valType);
    Constructor<?> valConstructor = currValClass.getConstructor(String.class);
    Instruction currVal = (Instruction) valConstructor.newInstance(valString);

    valueStack.push(currVal);
  }

  private InsnList createList()
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    Compiler listCompiler = new Compiler(this);
    userInputQueue.poll(); //get rid of opening bracket
    StringBuilder listString = new StringBuilder();
    //System.out.println("hits this method");
    while(!syntaxParser.getSymbol(userInputQueue.peek()).equals("ListEnd")) {
      //System.out.format("%s : %s\n" , userInputQueue.peek(), syntaxParser.getSymbol(userInputQueue.peek()));
      String thingAdded = userInputQueue.poll();
      if(thingAdded == null) throw new CompilerException(myErrorBundle.getString("noListEnd")); //have to add this error message still

      listString.append(thingAdded + " ");
    }
    userInputQueue.poll(); //get rid of ending bracket
    Deque<Instruction> listQueue = listCompiler.getCommands(listString.toString());
    InsnList listParam = new InsnList(listQueue, listCompiler.getVariableMap(), myModel);
    variablesMap.putAll(listCompiler.getVariableMap());
    userInstructionMap.putAll(listCompiler.getUserInstructionMap());
    return listParam;
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

  private void makeEmptyVariable() {
    String name = userInputQueue.poll();
    Variable newVar = new Variable(name, myModel);
    variablesMap.put(name, newVar);
    finalInstructionQueue.offer(newVar);
  }

  private void makeUserInstruction()
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    userInputQueue.poll(); //remove TO method call
    String name = userInputQueue.poll();
    InsnList variables = createList();
    InsnList insn = createList();
    UserInstruction newUserInsn = new UserInstruction(name, myModel, variables,insn, insn.getVarMap());
    userInstructionMap.put(name, newUserInsn);
  }

  private void finishCmdStack() {
    System.out.format("Cmd Stack: %d  |  Val Stack: %d\n", commandStack.size(), valueStack.size());
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

  public Map getUserInstructionMap() {
    return userInstructionMap;
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
