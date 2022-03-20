package slogo.Console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap.KeySetView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LanguageMenu {

  private final String RESOURCE_PATH = "slogo/languages/";
  private final String PYTHON_SCRIPT = "\\translator/translateJavaClass.py";
  ResourceBundle applicationBundle = null;
  ResourceBundle languages;
  private VBox pane = new VBox();
  private static final String TITLE = "Choose Your Language";
  private Stage myStage;
  private ComboBox chooseLanguage;
  private String myLanguage;

  public LanguageMenu() {
    myStage = new Stage();
    languages = ResourceBundle.getBundle(RESOURCE_PATH + "FixedLanguages");
    ArrayList<String> combined = new ArrayList<>();

    for (String str : languages.keySet())
      combined.add(str);
    combined.add("Choose Different Language");

    Label l1 = new Label("Choose a Language:");

    chooseLanguage = new ComboBox(FXCollections.observableArrayList(combined.toArray()));
    pane.getChildren().addAll(l1, chooseLanguage);
    addListener();

    Scene menu = new Scene(pane, 200, 275);
    myStage.setScene(menu);
    myStage.showAndWait();
  }

  private void addListener() {
    chooseLanguage.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
        String selectedLanguage = (String) chooseLanguage.getValue();
        if (selectedLanguage != null) {
          if (selectedLanguage.equals("Choose Different Language")){
            newLanguagePrompt();
            System.out.println(applicationBundle == null);
          }
          else{
            applicationBundle = ResourceBundle.getBundle( RESOURCE_PATH + selectedLanguage);
            myLanguage = selectedLanguage;
            }
          }
        if(applicationBundle != null)
          myStage.close();
        }
      });
  }


  public ResourceBundle getLanguageBundle() {
    return applicationBundle;
  }

  public String getAppLanguage(){
    return myLanguage;
  }

  private void newLanguagePrompt() {
    Stage s = new Stage();
    s.setTitle("As");
    VBox box = new VBox();
    // create a text input dialog
    TextField td = new TextField("Choose Real Language");
    Button d = new Button("click");
    d.setOnAction(e -> {
      if(makeResourcePack(td.getText()));
      myLanguage = td.getText();
      s.close();
    });
    box.getChildren().addAll(td,d);
    Scene sc = new Scene(box, 500, 300);
    s.setScene(sc);
    s.showAndWait();
  }



  private boolean makeResourcePack(String language) {
    ResourceBundle englishBundle  = ResourceBundle.getBundle(RESOURCE_PATH + "English");
    String currentWorkingDir = System.getProperty("user.dir");
    String finalPath = currentWorkingDir + PYTHON_SCRIPT;
    System.out.println(currentWorkingDir);
    System.out.println(finalPath);
    System.out.println("python " +finalPath +" " +"hello"+" "+language);

    for(String str: englishBundle.keySet()){
      System.out.println(str);
      try{
        ProcessBuilder builder = new ProcessBuilder("python " +finalPath +" " +str+" "+language);
        Process process = builder.start();
        int status = process.waitFor();

        try{
          ResourceBundle testbundle = ResourceBundle.getBundle(RESOURCE_PATH + language);
        }catch (Exception e){
          return false;
        }
      }catch (Exception e){
        return false;
      }
    }
    applicationBundle = ResourceBundle.getBundle(RESOURCE_PATH + language);
    return true;
  }
}
