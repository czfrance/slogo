package api.Controller;

import api.Exceptions.BadCharException;
import api.Exceptions.BadFileException;
import java.io.File;
import java.util.List;

/**
 * Internal API for the controller part of our program that helps parse user inputted text files into
 * instruction strings
 * @author Brandon Bae
 */
public interface FileParser {

  /**
   * Parses a text file of Logo code into individual instructions
   * @param txtFile The text file to parse
   * @return List of individual instruction strings
   * @throws BadFileException Error when file type is not of type .txt (also includes filenotfound exception)
   * @throws BadCharException If file contains a not supported ascii value
   */
  public List<String> parseTxtFile(File txtFile) throws BadFileException, BadCharException;

}
