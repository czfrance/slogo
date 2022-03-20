package slogo.View.Transitions.Rotations;

import java.util.Optional;
import javafx.animation.RotateTransition;
import javafx.util.Duration;
import slogo.Model.TurtleModel;
import slogo.View.TurtleView;

public class MovementRotate extends Rotate {

  public MovementRotate(Optional<Object> o, TurtleModel oldModel,
      TurtleModel myCurrModel, TurtleView myView) {
    super(o, oldModel, myCurrModel, myView);
  }

  @Override
  protected RotateTransition makeRotateTransition(Optional<Object> o, TurtleModel oldModel,
      TurtleModel myCurrModel, TurtleView myView) {
    RotateTransition rt = new RotateTransition();
    rt.setNode(myView);
    double angle = (double) o.get();
    rt.setDuration(Duration.seconds(Math.abs(angle) / TURTLE_TURN_SPEED));
    rt.setByAngle(angle);
    return rt;
  }
}
