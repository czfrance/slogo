package slogo.View;

import javafx.scene.Group;
import javafx.scene.Scene;
import slogo.Model.TurtleModel;

public class SketchbookView {

  TurtleModel myModel;
  TurtleView turtle;

  public SketchbookView(TurtleModel model) {
    myModel = model;
    turtle = makeTurtle();
  }

  Scene makeScene (int width, int height) {
    Group root = new Group();
    //turtle = makeActor();
    root.getChildren().add(turtle);
    return new Scene(root, width,  height);
  }

  public TurtleView makeTurtle() {

  }
}
