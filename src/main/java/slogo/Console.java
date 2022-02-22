package slogo;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
public class Console {


  private Compiler myCompiler;
  private Stage myStage;
  private ResourceBundle myResourceBundle;
  private static final String LANGUAGE_RESOURCE_PATH = "/slogo/languages/";
  private final static FileChooser FILE_CHOOSER = makeChooser(".txt");
  private Button uploadButton;
  private Scene popup;
  private TextArea myConsole;


  public Console(String language, Compiler compiler){
    myStage = new Stage();
    ResourceBundle resources = ResourceBundle.getBundle(LANGUAGE_RESOURCE_PATH + language);
    myCompiler = compiler;
    generatePopup();
  }

  public Console(String language){
    this(language, new Compiler(language));
  }

  private void generatePopup(){
    BorderPane layout = new BorderPane();
    layout.setPadding(new Insets(10,10,10,10));
    myConsole = new TextArea();

    myConsole.setWrapText(true);
    myConsole.setEditable(true);
    myConsole.setPromptText("Write Code here");
    layout.setCenter(myConsole);
    Button chooseScript = new Button("Load Script");
    Button enter = new Button("Enter Code");

    chooseScript.setOnAction(e->{
      try {
        File script = fileChoice(myStage);
        //System.out.println(fileToString(script));
        myCompiler.getCommands(fileToString(script));
      }
      catch(Exception err){
        generateAlert("File Error");
      }
    });
    enter.setOnAction(e->{
      if(!myConsole.getText().isEmpty()){
        try{
          myCompiler.getCommands(myConsole.getText());
        }catch(Exception err){
          generateAlert(err.getStackTrace().toString());
        }
      }
    });

    GridPane buttons = new GridPane();
    buttons.setHgap(230);
    buttons.add(chooseScript, 0,1);
    buttons.add(enter, 1,1);

    layout.setBottom(buttons);
    popup = new Scene(layout,400,400);
    myStage.setScene(popup);
    myStage.show();
  }

  private File fileChoice(Stage stage) {
    File dataFile = FILE_CHOOSER.showOpenDialog(stage);
    return dataFile;
  }

  private static FileChooser makeChooser (String extensionAccepted) {
    FileChooser result = new FileChooser();
    result.setTitle("Open Data File");
    // pick a reasonable place to start searching for files
    result.setInitialDirectory(new File(System.getProperty("user.dir")));
    result.getExtensionFilters().setAll(new ExtensionFilter("Text Files", extensionAccepted));
    return result;
  }

  private String fileToString(File file){
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

  private void generateAlert(String str){
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(str);
    alert.showAndWait();
  }
  public TextArea getConsole(){ return myConsole;}

}
