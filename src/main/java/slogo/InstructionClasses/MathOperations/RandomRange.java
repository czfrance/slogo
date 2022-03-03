package slogo.InstructionClasses.MathOperations;

import java.util.Random;
import slogo.CompilerExceptions.CompilerException;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleModel;

public class RandomRange extends MathOperation {
  public static final int RANDOM_RANGE_PARAM_NUM = 2;

  public RandomRange(TurtleCollection turtleModel) {
    super(RANDOM_RANGE_PARAM_NUM, turtleModel);
  }

  @Override
  public double returnValue() {
    double param1 = getMyParameters()[0].returnValue();
    double param2 = getMyParameters()[1].returnValue();
    if(param1>param2) {
      throw new CompilerException("RandomRange Error: Cannot have min > max");
    }
    //Maybe throws general instruction exception that states that rangeMin has to be < rangeMax
    Random r = new Random();
    double rangeMin = param1;
    double rangeMax = param2;
    return rangeMin + (r.nextDouble() * (rangeMax - rangeMin));
  }

  @Override
  public String toString() {
    double param1 = getMyParameters()[0].returnValue();
    double param2 = getMyParameters()[1].returnValue();
    return String.format("randomrange %f %f\n", param1, param2);
  }
}
