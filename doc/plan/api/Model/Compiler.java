package api.Model;

import api.Exceptions.MissingDependencyException;
import api.Exceptions.UndefinedMethodException;

/**
 * Internal API that parses any String into instructions and stores them in order in a queue. Also
 * can pop these instructions in order to support any other API's that might require an instruction
 * @author Brandon Bae
 */
public interface Compiler {

  /**
   * This method will validate and parse a string to be a valid SLogo instruction. This will then
   * push this new parsed and validated instruction to the instruction queue in model.
   *
   * @param rawInstruction The raw string of the user input to parse and validate
   * @param model The instruction model to push the parsed string to
   * @throws UndefinedMethodException If method does not exist in base instruction set or not a previously
   *                                  defined user method
   */
  public void validateString(String rawInstruction, InstructionModel model) throws UndefinedMethodException;

  /**
   * This is a getter method to get the instruction that was validated
   * @return
   */
  public String getValidInsn();
}
