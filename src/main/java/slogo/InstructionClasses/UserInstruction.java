package slogo.InstructionClasses;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;
import slogo.CompilerExceptions.CompilerException;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleModel;
import slogo.Model.TurtleRecord;
import slogo.PatternParser;

public class UserInstruction extends Instruction{

  private String myName;
  private PatternParser syntaxParser = new PatternParser();
  private Map<String, Variable> varMap;
  private Map<Integer, String> varIndices = new HashMap<Integer,String>();
  private InsnList myInstructions;

  public UserInstruction(UserInstruction parent) {
    super(parent.getNumParameters(), parent.getMyTurtles());
    myName = parent.myName;
    syntaxParser = parent.syntaxParser;
    varMap = new HashMap<String, Variable>(parent.varMap);
    varIndices = parent.varIndices;
    myInstructions = parent.myInstructions;
  }

  public UserInstruction(String name, TurtleCollection model, InsnList variables, InsnList instructions, Map<String, Variable> vars) {
    super(variables.getNumParameters(), model);
    myName = name;
    syntaxParser.addPatterns("Syntax");
    varMap = vars;
    checkVariables(variables);
    myInstructions = instructions;
  }

  private void checkVariables(InsnList variables) {
    Instruction[] varArray = variables.getMyParameters(); //make this create index list instead
    for(int i = 0; i<variables.getNumParameters(); i++) {
      if(!(varArray[i] instanceof Variable)) {
        throw new CompilerException("List Syntax: TO commandName\n"
            + "[ variable(s) ]\n"
            + "[ command(s) ]");
      }
      varIndices.put(i, varArray[i].toString().split(" ")[1]);
    }
  }

  /**
  private void setVarIndices() {
    int counter = 0;
    for(String key: myVars.keySet()) {
      System.out.println(key); //for testing purposes
      varIndices.put(counter, key);
      counter++;
    }
  }
   **/

  @Override
  public void setParameters(Stack<Instruction> valueStack) {
    int currVarIndex = getNumParameters()-1;
    System.out.println(getNumParameters());
    for(int i = currVarIndex; i>=0; i--) {
      Instruction currVarValue = valueStack.pop();
      String varName = varIndices.get(currVarIndex);
      System.out.println(varName); // for testing purposes
      varMap.get(varName).setVariable(currVarValue.returnValue());
    }
    valueStack.push(this);
  }

  @Override
  public double returnValue() {
    return 0;
  }

  @Override
  public BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda() {
    return null;
  }

  @Override
  public String toString() {
    return myInstructions.toString();
  }
}
