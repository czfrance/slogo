package slogo.InstructionClasses;

import java.util.Random;

public class RandomRange extends MathTwoParam{

  @Override
  public double returnValue() {
    //Maybe throws general instruction exception that states that rangeMin has to be < rangeMax
    Random r = new Random();
    double rangeMin = getParamOne();
    double rangeMax = getParamTwo();
    return rangeMin + (r.nextDouble() * (rangeMax - rangeMin));
  }

  @Override
  public String toString() {
    return String.format("randomrange %f %f\n", getParamOne(), getParamTwo());
  }
}
