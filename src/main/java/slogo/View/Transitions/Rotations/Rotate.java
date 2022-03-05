package slogo.View.Transitions.Rotations;

import java.util.Optional;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.util.Duration;
import slogo.Model.TurtleModel;
import slogo.View.TurtleView;

public abstract class Rotate {
  public static final int TURTLE_TURN_SPEED = 45; //degrees per second
  public static final double NO_MOVEMENT = 0.01; //pixels per second

  RotateTransition rotate;

  public Rotate(Optional<Object> o, TurtleModel oldModel, TurtleModel myCurrModel, TurtleView myView) {
    rotate = makeRotateTransition(o, oldModel, myCurrModel, myView);
  }

  public RotateTransition getRotate() {
    return rotate;
  }

  protected abstract RotateTransition makeRotateTransition(Optional<Object> o, TurtleModel oldModel,
      TurtleModel myCurrModel, TurtleView myView);
}
