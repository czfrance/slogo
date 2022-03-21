package slogo.InstructionClasses;

import java.util.Stack;
import java.util.function.BiFunction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleRecord;

public abstract class Instruction {

  private int myNumParameters;
  private int numParsedParameters = 0;

  private Instruction[] myParameters; //make this a list instead
  // change so public getter with immutable view of myParameterList
  // get an adder for the myParameters list
  // parameters is essentially the instruction list's instruction.

  private TurtleCollection myTurtles;

  private boolean isDone = false;

  public Instruction() {
    myNumParameters = 0;
  }

  public Instruction(Instruction parent) {
    myNumParameters = parent.myNumParameters;
    myParameters = parent.myParameters;
    myTurtles = parent.myTurtles;
  }

  public Instruction(int numParameters, TurtleCollection turtleModel) {
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

  public int getNumParsedParameters() {
    return numParsedParameters;
  }

  public void addNumParsedParameters() {
    numParsedParameters++;
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
    //System.out.printf("Turtle %d moving\n",myTurtles.getActiveTurtleID());
    myTurtles.runInsnOnTurtles(myParameters, getLambda());
  }

  /*
  protected boolean getAllActiveTurtlesRan() {
    return myTurtles.getNumActiveTurtlesRun()==myTurtles.getTotalActiveTurtles();
  }

   */

  protected Instruction[] getMyParameters() {
    return myParameters;
  }

  public Instruction getParamNum(int i) {
    return myParameters[i];
  }

  protected TurtleCollection getMyTurtles() {
    return myTurtles;
  }
}
