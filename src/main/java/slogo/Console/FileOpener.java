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

public class FileOpener {
  private final String[] EXTENSION_NAMES = { "Text File (*.txt)", "Slogo Script(*.slogo)"};
  private final String[] ALLOWABLE_EXTENSIONS= { "*.txt", "*.slogo"};
  private FileChooser myFileChooser;

  public FileOpener(){
    myFileChooser = makeFileChooser();
  }

  public File fileChoice(Stage stage) {
    File dataFile = myFileChooser.showOpenDialog(stage);
    return dataFile;
  }


  private FileChooser makeFileChooser() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose Script File");
    fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
    for(int i = 0; i<ALLOWABLE_EXTENSIONS.length; i++){
      fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(EXTENSION_NAMES[i], ALLOWABLE_EXTENSIONS[i]));
    }
    return fileChooser;
  }

  public String fileToString(File file){
    String content = null;
    try {
      content = Files.readString(Paths.get(file.getPath()));
      System.out.println(content);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return content;
  }

}
