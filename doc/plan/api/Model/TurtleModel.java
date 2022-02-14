package api.Model;

/**
 * The main external API for the Model part of our program. This will handle updating and storing all
 * turtle data based off instructions from the instruction queue.
 * @author Brandon Bae
 */
public interface TurtleModel {

  /**
   * Will pop the next instruction from its instruction stack and run it.
   * Will most likely use Java reflections to translate from string -> method
   */
  public void runNextInsn();

  /**
   * Getter Method for X-Y coord of turtle (one of turtle's query)
   * @return X-Y coord of turtle
   */
  public double[] getNextPos();

  /**
   * Getter Method for direction of turtle (one of turtle's query)
   * @return Degree of direction of turtle (0-360 degrees)
   */
  public double getHeading();

  /**
   * Getter method for pen status of turtle (one of turtle's query)
   * @return Boolean on whether or not pen is visible
   */
  public boolean getPenStatus();

  /**
   * Getter method for visible status of turtle (one of turtle's query)
   * @return Boolean on whether or not turtle is visible
   */
  public boolean getShowStatus();
}
