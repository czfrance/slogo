package slogo.CompilerPackage;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Stack;
import slogo.CompilerExceptions.CompilerException;
import slogo.CompilerExceptions.NotAValueException;
import slogo.InstructionClasses.Constant;
import slogo.InstructionClasses.Instruction;
import slogo.InstructionClasses.InsnList;
import slogo.InstructionClasses.UserInstruction;
import slogo.InstructionClasses.Variable;
import slogo.Model.TurtleCollection;
import slogo.PatternParser;

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
  private ListCreator listCreator;
  private ResourceBundle myErrorBundle;
  private TurtleCollection myTurtles;
  private String myLanguage;

  public Compiler(String language, TurtleCollection turtleModel) {
    myLanguage = language;
    myErrorBundle = ResourceBundle.getBundle(ERROR_RESOURCE_PACKAGE+"English");
    syntaxParser = new PatternParser();
    languageParser = new PatternParser();
    syntaxParser.addPatterns("Syntax");
    languageParser.addPatterns(language);
    myTurtles = turtleModel;
    listCreator = new ListCreator(variablesMap, userInstructionMap, myTurtles);
  }

  public Compiler(Compiler parent) {
    variablesMap = parent.variablesMap;
    userInstructionMap = parent.userInstructionMap;
    myLanguage = parent.myLanguage;
    myErrorBundle = parent.myErrorBundle;
    syntaxParser = parent.syntaxParser;
    languageParser = parent.languageParser;
    myTurtles = parent.myTurtles;
    listCreator = parent.listCreator;
  }

  public Deque<Instruction> getCommands(String userInput) throws CompilerException {
    try {
      clearStacks();
      makeUserInputStack(userInput);
      while(!userInputQueue.isEmpty()) {
        String currString = userInputQueue.peek();
        String currStringType = syntaxParser.getSymbol(currString);
        if(languageParser.getSymbol(currString).equals("MakeVariable")) {
          makeVariable();
        }
        else if (languageParser.getSymbol(currString).equals("MakeUserInstruction")) {
          makeUserInstruction();
        }
        if(currStringType.equals(PatternParser.NO_MATCH)) {
          String errorMessage = String.format(myErrorBundle.getString("unrecognizedCharacterMessage"), currString); // make error in package
          throw new CompilerException(errorMessage);
        }
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
    catch (CompilerException compilerException) {
      throw compilerException;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new CompilerException(myErrorBundle.getString("GeneralizedErrorMessage")); //make this error in bundle
    }

  }

  private void clearStacks() {
    finalInstructionQueue.clear();
    commandStack.clear();
    valueStack.clear();
  }

  private void commandMethod()
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, CompilerException {
    String cmdString = userInputQueue.poll();
    String translatedCmd = languageParser.getSymbol(cmdString);
    Instruction currCmd;
    if(translatedCmd.equals(PatternParser.NO_MATCH)) {
      currCmd = userInstructionMap.get(cmdString);
      if(currCmd == null) throw new CompilerException(myErrorBundle.getString("noSuchInstructionError")); //haven't made this error bundle yet
      currCmd = new UserInstruction(userInstructionMap.get(cmdString));
    }
    else {
      Class<?> currCmdClass = Class.forName(INSTRUCTION_PACKAGE + INSTRUCTION_TYPE_BUNDLE.getString(translatedCmd) + "." + translatedCmd);
      Constructor<?> cmdConstructor = currCmdClass.getConstructor(new Class[]{TurtleCollection.class});
      currCmd = (Instruction) cmdConstructor.newInstance(myTurtles);
    }
    if(getCmdParam(currCmd)){
      currCmd.setParameters(valueStack);
      finishCmdStack();
    }
  }

  private boolean getCmdParam(Instruction currCmd)
      throws CompilerException {
    int currNumParsedParameters = currCmd.getNumParsedParameters();
    for(int i = 0; i<currCmd.getNumParameters()-currNumParsedParameters; i++) {

      if(userInputQueue.size() == 0) {
        throw new CompilerException(myErrorBundle.getString("numParamError"), currCmd.getClass().toString(), currCmd.getNumParameters());//need to add to error package
      }

      try{
        paramMethod();
      }
      catch (NotAValueException notVal) {
        commandStack.push(currCmd);
        return false;
      }
      catch (Exception e) {
        throw new CompilerException(myErrorBundle.getString("GeneralErrorMessage"));
      }
      currCmd.addNumParsedParameters();
    }
    finalInstructionQueue.offer(currCmd);
    return true;
  }

  private void finishCmdStack() {
    while(!commandStack.isEmpty()) {
      Instruction currCmd = commandStack.pop();
      for(int i = 0; i<valueStack.size(); i++) currCmd.addNumParsedParameters();
      if(!getCmdParam(currCmd)) {
        break;
      }
      currCmd.setParameters(valueStack);
    }

  }

  private void constantMethod() {
    String constantString = userInputQueue.poll();
    Constant loneConstant = new Constant(constantString);
    finalInstructionQueue.offer(loneConstant);
  }

  private void paramMethod()
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NotAValueException {
    String valString = userInputQueue.peek();
    String valType = syntaxParser.getSymbol(valString);
    //make these into the reflection from resource bundle thing
    if(valType.equals("Variable")) {
      userInputQueue.poll();
      Variable varValue = variablesMap.get(valString);
      if(varValue == null) {
        throw new CompilerException("Variable %d never initialized", valString);
      }
      valueStack.push(varValue);
      return;
    }
    else if(valType.equals("ListStart") || valType.equals("ListEnd")) {

      InsnList listParam = listCreator.createList(userInputQueue, this);
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

  private void makeVariable()
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NotAValueException {
    userInputQueue.poll(); //remove makeVar method call
    String name = userInputQueue.poll();

    Variable newVar = new Variable(name, myTurtles);
    variablesMap.put(name, newVar);
    finalInstructionQueue.offer(newVar);
    try{
      paramMethod();
    }
    catch (Exception NotAValueException) {
      commandStack.push(newVar);
      return;
    }
    newVar.setParameters(valueStack);
  }

  private void makeEmptyVariable() {
    String name = userInputQueue.poll();
    Variable newVar = new Variable(name, myTurtles);
    variablesMap.put(name, newVar);
    finalInstructionQueue.offer(newVar);
  }

  private void makeUserInstruction()
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    userInputQueue.poll(); //remove TO method call
    String name = userInputQueue.poll();
    InsnList variables = listCreator.createList(userInputQueue, this);
    InsnList insn = listCreator.createList(userInputQueue, this);
    UserInstruction newUserInsn = new UserInstruction(name, myTurtles, variables,insn, insn.getVarMap());
    userInstructionMap.put(name, newUserInsn);
  }

  private void makeUserInputStack(String userInput) {
    if(userInput.isBlank()) return;
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
