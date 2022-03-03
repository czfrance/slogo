package slogo.InstructionClasses;

import java.util.Stack;
import java.util.function.BiFunction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleModel;
import slogo.Model.TurtleRecord;
import slogo.PatternParser;

public abstract class Instruction {

  private int myNumParameters;

  private PatternParser valueParser = new PatternParser();

  private Instruction[] myParameters; //make this a list instead
  // change so public getter with immutable view of myParameterList
  // get an adder for the myParameters list
  // parameters is essentially the instruction list's instruction.

  private TurtleCollection myTurtles;

  private boolean isDone = false;

  public Instruction() {
    valueParser.addPatterns("Syntax");
    myNumParameters = 0;
  }

  public Instruction(int numParameters, TurtleCollection turtleModel) {
    valueParser.addPatterns("Syntax");
    myNumParameters = numParameters;
    myParameters = new Instruction[numParameters];
    myTurtles = turtleModel;
  }

  protected void setIsDone(boolean bool) {
    isDone = bool;
  }

  public boolean getIsDone() {
    return isDone;
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

  public double frontEndReturnValue() {
    return returnValue();
  }

  public abstract BiFunction<Instruction[], TurtleRecord, TurtleRecord> getLambda();

  public void run() {
    isDone = true;
    myTurtles.runInsn(myParameters, getLambda());
  }

  protected Instruction[] getMyParameters() {
    return myParameters;
  }
  protected TurtleCollection getMyTurtles() {
    return myTurtles;
  }

  public boolean isValueType(String desiredValueType, String poppedValue) {
    String currType = valueParser.getSymbol(poppedValue);
    return desiredValueType.equals(currType);
  }
}
