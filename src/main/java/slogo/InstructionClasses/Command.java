package slogo.InstructionClasses;

import java.util.List;
import java.util.Stack;
import slogo.PatternParser;

public abstract class Command {

  private PatternParser valueParser = new PatternParser();;

  public Command(){
    valueParser.addPatterns("Syntax");
  }

  public abstract void setParameters(Stack<Command> valueStack);

  public abstract int getNumParameters();

  public abstract double returnValue();

  public boolean isValueType(String desiredValueType, String poppedValue) {
    String currType = valueParser.getSymbol(poppedValue);
    return desiredValueType.equals(currType);
  }
}
