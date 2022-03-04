package slogo.Console;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class LanguageMenu {

  private final String RESOURCE_PATH = "slogo/languages/";
  ResourceBundle applicationBundle;
  ResourceBundle languages;
  private BorderPane pane = new BorderPane();
  private static final String TITLE = "Choose Your Language";
  private Stage myStage;
  private ComboBox chooseLanguage;

  public LanguageMenu() {
    myStage = new Stage();
    languages = ResourceBundle.getBundle(RESOURCE_PATH + "FixedLanguages");
    String[] arr = (String[]) languages.keySet().toArray();
    ArrayList<String> combined = new ArrayList<>();

    for (String str : languages.keySet())
      combined.add(languages.getString(str));
    combined.add("Choose Different Language");

    Label l1 = new Label("Choose a Language:");

    chooseLanguage = new ComboBox(FXCollections.observableArrayList(combined.toArray()));
    pane.setAlignment(Pos.CENTER);

    addListener();

    menu = new Scene(pane, 200, 275);
    myStage.showAndWait();
  }

  private void addListener() {
    chooseLanguage.accessibleTextProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
        String selectedLanguage = (String) chooseLanguage.getValue();
        if (selectedLanguage != null) {
          if (selectedLanguage.equals("Choose Different Language")) {
            selectedLanguage = null;
            selectedLanguage = newLanguagePrompt();
          }
          applicationBundle = ResourceBundle.getBundle(
              RESOURCE_PATH + languages.getString(selectedLanguage));
          myStage.close();
        }
      }
    });
  }

  public ResourceBundle getLanguageBundle() {
    return applicationBundle;
  }

  private String newLanguagePrompt() {
    Stage s = new Stage();
    s.setTitle("As");
    TilePane r = new TilePane();

    // create a text input dialog
    TextInputDialog td = new TextInputDialog("Choose a Real Language");

    // setHeaderText
    td.setHeaderText("Enter a language");

    // create a button
    Button d = new Button("click");
    d.setOnAction(e -> {
      try {
        makeResourcePack(td.getEditor().getText());
      }catch (Exception e) {

    })
      r.getChildren().add(d);
    Scene sc = new Scene(r, 500, 300);
    s.setScene(sc);
    s.showAndWait();
    ;
  }
}


  private void makeResourcePack(String language){
    ResourceBundle englishBundle  = ResourceBundle.getBundle(RESOURCE_PATH + "English");
    Set<String> instructions = (Set<String>) englishBundle.getKeys();

    for(String str: instructions){

      try{
        ResourceBundle testbundle = ResourceBundle.getBundle(RESOURCE_PATH + language);
      }catch (Exception e){
        throw new NullPointerException();
      }
    }
  }
}
