package slogo.InstructionClasses.MathOperations;

import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleModel;

public class Product extends MathOperation {
  public static final int PRODUCT_PARAM_NUM = 2;

  public Product(TurtleCollection turtleModel) {
    super(PRODUCT_PARAM_NUM, turtleModel);
  }

  @Override
  public double returnValue() {
    double param1 = getMyParameters()[0].returnValue();
    double param2 = getMyParameters()[1].returnValue();
    return param1*param2;
  }

  @Override
  public String toString() {
    double param1 = getMyParameters()[0].returnValue();
    double param2 = getMyParameters()[1].returnValue();
    return String.format("product %f %f\n", param1, param2);
  }
}
