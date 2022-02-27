package slogo.InstructionClasses;

import java.util.Stack;
import java.util.function.BiFunction;
import slogo.Model.TurtleModel;
import slogo.Model.TurtleRecord;
import slogo.PatternParser;

public abstract class Instruction {

  private int myNumParameters;

  private PatternParser valueParser = new PatternParser();

  private Instruction[] myParameters;

  private TurtleModel myTurtleModel;

  public Instruction() {
    valueParser.addPatterns("Syntax");
    myNumParameters = 0;
  }

  public Instruction(int numParameters, TurtleModel turtleModel) {
    valueParser.addPatterns("Syntax");
    myNumParameters = numParameters;
    myParameters = new Instruction[numParameters];
    myTurtleModel = turtleModel;
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

  public abstract BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda();

  public void run() {
    myTurtleModel.runInsn(myParameters, getLambda());
  }

  protected Instruction[] getMyParameters() {
    return myParameters;
  }

  public boolean isValueType(String desiredValueType, String poppedValue) {
    String currType = valueParser.getSymbol(poppedValue);
    return desiredValueType.equals(currType);
  }
}
