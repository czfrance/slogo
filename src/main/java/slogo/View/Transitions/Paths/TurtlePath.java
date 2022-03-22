package slogo.View.Transitions.Paths;

import java.util.Optional;
import javafx.animation.PathTransition;
import javafx.scene.Group;
import slogo.Model.TurtleModel;
import slogo.View.TurtleView;

public class TurtlePath {
  public static int X = 0;
  public static int Y = 1;

  PathTransition turtlePath;

  public TurtlePath(Optional<Object> o, TurtleModel oldModel, TurtleModel myCurrModel, TurtleView myView, Group root) {
    turtlePath = makePathTransition(o, oldModel, myCurrModel, myView, root);
  }

  public PathTransition getPathTransition() {
    return turtlePath;
  }

  private PathTransition makePathTransition(Optional<Object> o, TurtleModel oldModel,
      TurtleModel myCurrModel, TurtleView myView, Group root) {

    if (o.isPresent() && moved(oldModel.getNextPos(), myCurrModel)) {
      return new MovementPath(o, oldModel, myCurrModel, myView, root).getPath();
    } else {
      return new DoNothingPath(o, oldModel, myCurrModel, myView, root).getPath();
    }
  }

  private boolean moved(double[] nextPos, TurtleModel myCurrModel) {
    return (nextPos[X] != myCurrModel.getNextPos()[X] || nextPos[Y] != myCurrModel.getNextPos()[Y]);
  }
}