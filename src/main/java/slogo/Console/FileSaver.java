package slogo.Console;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import javax.xml.stream.FactoryConfigurationError;

public class FileSaver {

  private static final Character[] INVALID_WINDOWS_SPECIFIC_CHARS ={'"', '*', ':', '<', '>', '?', '\\', '|', 0x7F};
  private final Character[] INVALID_UNIX_SPECIFIC_CHARS = {'\000'};
  private Stage myStage;
  private GridPane layout;
  public static final String FILE_PATH = "data" + File.separator;
  private ArrayList<String> dataToSave;

  public FileSaver(ArrayList<String> history){
    dataToSave = history;
    layout = new GridPane();
    layout.setPadding(new Insets(10, 10, 10, 10));
    layout.setVgap(5);
    layout.setHgap(5);
    final TextField filename = new TextField();
    filename.setPromptText("FileName");
    final TextField author = new TextField();
    author.setPromptText("Author");
    final TextField description = new TextField();
    description.setPromptText("Description");
    layout.add(filename, 0,1 , 10, 1);
    layout.add(author, 0, 2, 10, 1);
    layout.add(description, 0,3, 10, 1);
    Button generate = new Button("Save File");

    generate.setOnAction(e->{
      if(validateFileName(filename.getText())){
        try {
          saveInstructions(author.getText(), filename.getText(), description.getText());
          myStage.close();
        }
        catch (Exception error) {

        }
      }else{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Invalid FileName");
        alert.showAndWait();
      }
    });
    layout.add(generate,5,5);
    Scene scene = new Scene(layout, 300, 300);
    myStage = new Stage();
    myStage.setScene(scene);
    myStage.show();
  }


  private void saveInstructions(String author, String filename, String description) {
    String header = generateHeader(author, filename, description);
    try {
      File myFile = new File(FILE_PATH + filename + ".txt");
      FileWriter fileWriter = new FileWriter(myFile,false);
      fileWriter.write(header);
      for(String str: dataToSave){
        System.out.println("test: " + str);
        fileWriter.write(str);
        fileWriter.flush();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private String generateHeader(String author, String filename, String description){
    StringBuilder str = new StringBuilder();
    str.append( String.format("# ----------------------------\n"));
    str.append( String.format("# @title: %s\n", filename));
    str.append(String.format("# @author: %s\n", author));
    str.append( String.format("# @description: %s\n", description));
    str.append(String.format("# @date: %s\n", java.time.LocalDate.now()));
    str.append(String.format("# ----------------------------\n"));
    return str.toString();
  }

  private boolean validateFileName(String filename){
    if (filename == null || filename.isEmpty() || filename.length() > 255) {
      return false;
    }
    return Arrays.stream(getInvalidCharsByOS())
        .noneMatch(ch -> filename.contains(ch.toString()));
  }

  private Character[] getInvalidCharsByOS() {
    String os = System.getProperty("os.name").toLowerCase();
    if (os.contains("win")) {
      return INVALID_WINDOWS_SPECIFIC_CHARS;
    } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
      return INVALID_UNIX_SPECIFIC_CHARS;
    } else {
      return new Character[]{};
    }
  }
  private static String listToString(List<Double> list) {
    StringBuilder str = new StringBuilder();
    for (Double element: list) {
      str.append(element).append(" ");
    }
    return str.toString();
  }

}


