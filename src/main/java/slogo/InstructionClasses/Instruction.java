package slogo.InstructionClasses;

import java.util.Stack;
import slogo.PatternParser;

public abstract class Instruction {

  private int myNumParameters;

  private PatternParser valueParser = new PatternParser();

  private Instruction[] myParameters;

  public Instruction() {
    valueParser.addPatterns("Syntax");
    myNumParameters = 0;
  }

  public Instruction(int numParameters) {
    valueParser.addPatterns("Syntax");
    myNumParameters = numParameters;
    myParameters = new Instruction[numParameters];
  }

  public void setParameters(Stack<Instruction> valueStack) {
    int currParamIndex = getNumParameters()-1;
    for(int i = currParamIndex; i>=0; i--) {
      Instruction currParam = valueStack.pop();
      getMyParameters()[i] = currParam;
    }
    valueStack.push(this);
  }

  public int getNumParameters() {
    return myNumParameters;
  }

  public abstract double returnValue();

  public abstract void run();

  protected Instruction[] getMyParameters() {
    return myParameters;
  }

  public boolean isValueType(String desiredValueType, String poppedValue) {
    String currType = valueParser.getSymbol(poppedValue);
    return desiredValueType.equals(currType);
  }
}
