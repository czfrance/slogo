package slogo.View;

import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import slogo.Model.TurtleModel;

public class SketchbookView {
  public static final Dimension DEFAULT_SIZE = new Dimension(400, 400);
  TurtleModel myModel;
  TurtleView turtle;

  public SketchbookView(TurtleModel model) {
    myModel = model;
    turtle = makeTurtle();
  }

  public Scene makeScene () {
    Group root = new Group();
    root.getChildren().add(turtle);
    return new Scene(root, DEFAULT_SIZE.width, DEFAULT_SIZE.height);

  }

  public TurtleView makeTurtle() {
    return new TurtleView(convertX(myModel.getNextPos()[0]), convertY(myModel.getNextPos()[1]),
        myModel.getHeading(), "turtleOutline", Color.RED);
  }

  public Animation makeAnimation ()
      throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
    // create something to follow

    MoveTo move = new MoveTo(convertX(myModel.getNextPos()[0]), convertY(myModel.getNextPos()[1]));
    myModel.runNextInsn();
    Path path = new Path();
    path.getElements().addAll(move,
        new LineTo(convertX(myModel.getNextPos()[0]), convertY(myModel.getNextPos()[1])));
    // create an animation where the shape follows a path
    PathTransition pt = new PathTransition(Duration.seconds(4), path, turtle);
//    // create an animation that rotates the shape
//    RotateTransition rt = new RotateTransition(Duration.seconds(3));
//    rt.setByAngle(90);
//    // put them together in order
//    return new SequentialTransition(turtle, pt);
    return pt;
  }

  private double convertX(double modelX) {
    return modelX + (DEFAULT_SIZE.width / 2);
  }

  private double convertY(double modelY) {
    return (DEFAULT_SIZE.height / 2) - modelY;
  }

  public void updateTurtle(double x, double y, double heading, Color color) {
    turtle.updateTurtle(x, y, heading, color);
  }
}
