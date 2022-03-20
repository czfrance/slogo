package slogo.Console.Views.InteractiveNodes;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import slogo.InstructionClasses.Instruction;
import slogo.Model.TurtleCollection;
import slogo.Model.TurtleInsnModel;

public class InstructionGUI {

  private String myName;

  private TurtleInsnModel myModel;
  private TurtleCollection myTurtles;
  private int numOfParameters;
  private Scene myScene;
  private Stage myStage;
  public InstructionGUI(String title, TurtleInsnModel myModel, TurtleCollection myTurtles) {
    myStage = new Stage();
    this.myName = title;
    this.myModel = myModel;
    this.myTurtles = myTurtles;
    ResourceBundle parameterNumber = ResourceBundle.getBundle("slogo/languages/parameterNumber");
    numOfParameters = Integer.parseInt(parameterNumber.getString(title));
    makeGui();
    myStage.setScene(myScene);
    myStage.show();
  }

  private void makeGui() {
    GridPane pane = new GridPane();
    pane.setPadding(new Insets(10,10,10,10));
    pane.setVgap(10);
    pane.setHgap(25);
    pane.setAlignment(Pos.CENTER);
    Set ids = myTurtles.getCreatedTurtleMap().keySet();
    ComboBox dropdown = new ComboBox(FXCollections.observableArrayList(ids.toArray()));
    pane.add(dropdown, 2, 0, 1, 2);
    Button b = new Button("Run");
    pane.add(b, 3, 1, 1, 2);
    ArrayList<Slider> parameters = new ArrayList<>();
    for(int i = 0; i<numOfParameters; i++){
      parameters.add(makeSlider());
      Label l = new Label("Parameter " + (i+1));
      pane.add(l, 0, i+3, 2,2);
      pane.add(parameters.get(i), 1, 4, 10, 10);
    }
    b.setOnAction(e->{
      if(dropdown.getValue()!=null)
        executeCommand((Integer) dropdown.getValue(), parameters);
    });
    myScene = new Scene(pane);
  }

  private void executeCommand(Integer id, ArrayList<Slider> parameters) {
    int turtle = id;
    String n = myName + " ";
    for(Slider slider :parameters){
      n += slider.getValue() + " ";
    }
    try{
      myModel.addUserInput("tell [ " + turtle + " ]");
      myModel.addUserInput(n);
    }catch (Exception e){
    }
  }

  private Slider makeSlider(){
    Slider slider = new Slider();
    slider.setOrientation(Orientation.HORIZONTAL);
    slider.setMin(-100);
    slider.setMax(100);
    slider.setValue(0);
    slider.setShowTickLabels(true);
    slider.setShowTickMarks(true);
    slider.setMajorTickUnit(50);
    slider.setMinorTickCount(25);
    slider.setBlockIncrement(5);
    return slider;
  }
}
