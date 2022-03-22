package slogo.View.Transitions.Paths;

import java.util.Optional;
import javafx.animation.PathTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.util.Duration;
import slogo.Model.TurtleModel;
import slogo.View.TurtleView;

public class MovementPath extends Path {
  public static int TURTLE_SPEED = 50; //pixels per second

  public MovementPath(Optional<Object> o, TurtleModel oldModel, TurtleModel myCurrModel, TurtleView myView, Group root) {
    super(o, oldModel, myCurrModel, myView, root);
  }

  @Override
  protected PathTransition makePathTransition(Optional<Object> o, TurtleModel oldModel,
      TurtleModel myCurrModel, TurtleView myView, Group root) {
    MoveTo move = new MoveTo(convertX(oldModel.getNextPos()[X]),
        convertY(oldModel.getNextPos()[Y]));
    javafx.scene.shape.Path path = new javafx.scene.shape.Path();
    path.getElements().addAll(move,
        new LineTo(convertX(myCurrModel.getNextPos()[X]), convertY(myCurrModel.getNextPos()[X])));
    Duration pathAnimDuration;
    pathAnimDuration = Duration.seconds(Math.abs((double) o.get()) / TURTLE_SPEED);
    PathTransition pathTrans = new PathTransition(pathAnimDuration, path, myView);
    setListener(pathTrans, myCurrModel, myView, root);
    return pathTrans;
  }

  private void setListener(PathTransition pathTrans, TurtleModel myCurrModel, TurtleView myView, Group root) {
    pathTrans.currentTimeProperty().addListener( new ChangeListener<>() {

      double[] oldLocation = null;

      /**
       * Draw a line from the old location to the new location
       */
      @Override
      public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {

        //for(int i: myInsnModel.getActiveIDList()) {
        //TurtleModel myCurrModel = myInsnModel.getCreatedTurtleMap().get(i);
        // skip starting at 0/0
        if (oldValue == Duration.ZERO)
          return;

        // get current location
        double x = myView.getBoundsInParent().getCenterX();
        double y = myView.getBoundsInParent().getCenterY();

        // initialize the location
        if (oldLocation == null) {
          oldLocation = new double[]{x, y};
          return;
        }

        // draw line
        if (myCurrModel.getTurtleRecord().isPenDown()) {
          root.getChildren().add(
              myView.getPen().draw(oldLocation[X], oldLocation[Y], x, y));
        }

        // update old location with current one
        oldLocation[X] = x;
        oldLocation[Y] = y;
        //}
      }
    });
  }
}
