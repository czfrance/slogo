package slogo.View.Transitions.Rotations;

import java.util.Optional;
import javafx.animation.RotateTransition;
import javafx.util.Duration;
import slogo.Model.TurtleModel;
import slogo.View.TurtleView;

public class DoNothingRotate extends Rotate {

  public DoNothingRotate(Optional<Object> o, TurtleModel oldModel,
      TurtleModel myCurrModel, TurtleView myView) {
    super(o, oldModel, myCurrModel, myView);
  }

  @Override
  protected RotateTransition makeRotateTransition(Optional<Object> o, TurtleModel oldModel,
      TurtleModel myCurrModel, TurtleView myView) {
    RotateTransition rt = new RotateTransition();
    rt.setNode(myView);
    rt.setDuration(Duration.seconds(NO_MOVEMENT));
    rt.setByAngle(0);
    return rt;
  }
}
