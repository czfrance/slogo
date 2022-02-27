/**
 * @Author: Prajwal Jagadish
 *
 * Class that handles all the file i/o for SLOGO.
 * - i.e Choosing the file to upload
 * - Saving the file correctly
 */

package slogo.Console;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FileHandler {
  private final static FileChooser FILE_CHOOSER = makeFileChooser(".txt");

  public FileHandler(){

  }


  public File fileChoice(Stage stage) {
    File dataFile = FILE_CHOOSER.showOpenDialog(stage);
    return dataFile;
  }


  private static FileChooser makeFileChooser(String extensionAccepted) {
    FileChooser result = new FileChooser();
    result.setTitle("Choose Script File");
    // pick a reasonable place to start searching for files
    result.setInitialDirectory(new File(System.getProperty("user.dir")));
    result.getExtensionFilters().setAll(new ExtensionFilter("Text Files", extensionAccepted));
    return result;
  }

  public String fileToString(File file){
    String content = null;
    try {
      // default StandardCharsets.UTF_8
      content = Files.readString(Paths.get(file.getPath()));
      System.out.println(content);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return content;
  }

}
