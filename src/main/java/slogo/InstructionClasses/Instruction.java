package slogo.InstructionClasses;

import java.util.Stack;
import slogo.PatternParser;

public abstract class Instruction {

  private int myNumParameters;

  private PatternParser valueParser = new PatternParser();;

  public Instruction() {
    valueParser.addPatterns("Syntax");
    myNumParameters = 0;
  }

  public Instruction(int numParameters) {
    valueParser.addPatterns("Syntax");
    myNumParameters = numParameters;
  }

  public abstract void setParameters(Stack<Instruction> valueStack);

  public int getNumParameters() {
    return myNumParameters;
  }

  public abstract double returnValue();

  public abstract void run();

  public boolean isValueType(String desiredValueType, String poppedValue) {
    String currType = valueParser.getSymbol(poppedValue);
    return desiredValueType.equals(currType);
  }
}
