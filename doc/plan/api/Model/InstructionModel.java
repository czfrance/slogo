package api.Model;

import api.Exceptions.MissingDependencyException;

/**
 * Every turtle instance will have an instruction model which handles instruction order for the turtle.
 * @Brandon Bae
 */
public interface InstructionModel {
  /**
   * Will pop the next instruction in its instruction queue and return its string representation.
   * Mainly will be used by TurtleModel to see what the turtle should do next.
   * @return String The instruction to be called by TurtleModel
   * @throws MissingDependencyException Error if Instruction asks for variable not defined previously
   */
  public String getNextInsn() throws MissingDependencyException;

  /**
   * Pushes the new instruction into the instruction queue
   * @param instruction the instruction to push
   */
  public void pushInsn(String instruction);
}
