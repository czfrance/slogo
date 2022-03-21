package slogo.View.Transitions.Paths;

import java.awt.Dimension;
import java.util.Optional;
import javafx.animation.PathTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import slogo.Model.TurtleModel;
import slogo.View.TurtleView;

public class TurtlePath {

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
    return (nextPos[0] != myCurrModel.getNextPos()[0] || nextPos[1] != myCurrModel.getNextPos()[1]);
  }
}
