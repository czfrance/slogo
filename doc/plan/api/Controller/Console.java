package api.Controller;

import java.util.List;

/**
 * The console will act as the main controller class and will act as an external API that will provide
 * parsed user input strings. Does not handle any parsing of strings for specific instructions simply provides
 * raw instruction strings
 *
 * Assumptions:
 * - Only ways for Users to send commands to program is through console input or uploading txt file
 * @author Brandon Bae
 */
public interface Console {

  /**
   * Returns the a list of strings with each string representing individual instructions
   * @author Brandon Bae
   */
  public List<String> getUserInput();



}
