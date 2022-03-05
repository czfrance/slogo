package slogo.View.Transitions.Rotations;

import java.util.Optional;
import javafx.animation.RotateTransition;
import slogo.Model.TurtleModel;
import slogo.View.TurtleView;

public class TurtleRotation {

  RotateTransition turtleRotation;

  public TurtleRotation(Optional<Object> o, TurtleModel oldModel, TurtleModel myCurrModel, TurtleView myView) {
    turtleRotation = makeRotateTransition(o, oldModel, myCurrModel, myView);
  }

  public RotateTransition getTurtleRotation() {
    return turtleRotation;
  }

  private RotateTransition makeRotateTransition(Optional<Object> o, TurtleModel oldModel, TurtleModel myCurrModel, TurtleView myView) {
    if (o.isPresent() && !(oldModel.getHeading() == myCurrModel.getHeading())) {
      return new MovementRotate(o, oldModel, myCurrModel, myView).getRotate();
    } else {
      return new DoNothingRotate(o, oldModel, myCurrModel, myView).getRotate();
    }
  }
}
