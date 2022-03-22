package slogo.View.Transitions.Paths;

import java.awt.Dimension;
import java.util.Optional;
import javafx.animation.PathTransition;
import javafx.scene.Group;
import slogo.Model.TurtleModel;
import slogo.View.TurtleView;

public abstract class Path {
  public static final Dimension DEFAULT_SIZE = new Dimension(800, 600);
  public static int X = 0;
  public static int Y = 1;

  PathTransition path;
  public Path(Optional<Object> o, TurtleModel oldModel, TurtleModel myCurrModel, TurtleView myView, Group root) {
    path = makePathTransition(o, oldModel, myCurrModel, myView, root);
  }

  public PathTransition getPath() {
    return path;
  }

  protected abstract PathTransition makePathTransition(Optional<Object> o, TurtleModel oldModel,
      TurtleModel myCurrModel, TurtleView myView, Group root);

  protected double convertX(double modelX) {
    return modelX + (DEFAULT_SIZE.width / 2);
  }

  protected double convertY(double modelY) {
    return (DEFAULT_SIZE.height / 2) - modelY;
  }
}
