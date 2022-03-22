package slogo.View.Transitions.Paths;

import java.util.Optional;
import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.util.Duration;
import slogo.Model.TurtleModel;
import slogo.View.TurtleView;

public class DoNothingPath extends Path {
  public static final double NO_MOVEMENT_TIME = 0.01; //pixels per second
  public static final double NO_MOVEMENT_DISTANCE = 0.01; //pixels per second

  public DoNothingPath(Optional<Object> o, TurtleModel oldModel, TurtleModel myCurrModel, TurtleView myView, Group root) {
    super(o, oldModel, myCurrModel, myView, root);
  }

  @Override
  protected PathTransition makePathTransition(Optional<Object> o, TurtleModel oldModel,
      TurtleModel myCurrModel, TurtleView myView, Group root) {
    MoveTo move = new MoveTo(convertX(oldModel.getNextPos()[X]),
        convertY(oldModel.getNextPos()[Y]));
    javafx.scene.shape.Path path = new javafx.scene.shape.Path();
    path.getElements().addAll(move,
        new LineTo(convertX(myCurrModel.getNextPos()[X]), convertY(myCurrModel.getNextPos()[Y] - NO_MOVEMENT_DISTANCE)),
        new LineTo(convertX(oldModel.getNextPos()[X]), convertY(oldModel.getNextPos()[Y])));

    return new PathTransition(Duration.seconds(NO_MOVEMENT_TIME), path, myView);
  }
}
