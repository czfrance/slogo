package slogo.View;

import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
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
  public static final int TURTLE_SPEED = 50; //pixels per second
  public static final int TURTLE_TURN_SPEED = 45; //degrees per second
  public static final double NO_MOVEMENT = 0.01; //pixels per second

  TurtleModel myModel;
  TurtleView turtle;

  public SketchbookView(TurtleModel model) {
    myModel = model;
    turtle = makeTurtle();
  }

  public Scene makeScene() {
    Group root = new Group();
    root.getChildren().add(turtle);
    return new Scene(root, DEFAULT_SIZE.width, DEFAULT_SIZE.height);

  }

  public TurtleView makeTurtle() {
    return new TurtleView(convertX(myModel.getNextPos()[0]), convertY(myModel.getNextPos()[1]),
        myModel.getHeading(), "turtle", Color.RED);
  }

  public void play() {
    try {
      Animation animation = makeAnimation();
      animation.play();
      animation.setOnFinished(e -> play());
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  public Animation makeAnimation()
      throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
    TurtleModel oldModelState = new TurtleModel(myModel.getNextPos()[0], myModel.getNextPos()[1],
        myModel.getHeading());
    Optional<Object> o = myModel.runNextInsn();
    if (o.isPresent()) {
      return getTransition(o, oldModelState);
    } else {
      return doNothingPath(oldModelState);
    }
  }
  /*
   todo:
    figure out how all this ties in with turtleView
  */

  private Transition getTransition(Optional<Object> o, TurtleModel oldModel)
      throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
    PathTransition pt = getPathTransition(o, oldModel);
    RotateTransition rt = getRotateTransition(o, oldModel);
    return new SequentialTransition(turtle, pt, rt);
  }

  private Optional<Method> getChangeFunction(TurtleModel oldModel)
      throws NoSuchMethodException {
    if (!oldModel.getNextPos().equals(myModel.getNextPos())) {
      return Optional.of(this.getClass()
          .getDeclaredMethod("getPathTransition", Optional.class, TurtleModel.class, MoveTo.class));
    } else if (!(oldModel.getHeading() == myModel.getHeading())) {
      return Optional.of(this.getClass()
          .getDeclaredMethod("getRotateTransition", Optional.class, TurtleModel.class));
    }
    return Optional.empty();
  }

  private RotateTransition getRotateTransition(Optional<Object> o,
      TurtleModel oldModel) {
    RotateTransition rt = new RotateTransition();
    rt.setNode(turtle);
    if (o.isPresent() && !(oldModel.getHeading() == myModel.getHeading())) {
      int angle = (int) o.get();
      rt.setDuration(Duration.seconds(Math.abs(angle) / TURTLE_TURN_SPEED));
      rt.setByAngle(angle);
      return rt;
    } else {
      return doNothingRotate();
    }
  }

  private PathTransition getPathTransition(Optional<Object> o, TurtleModel oldModel)
      throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
    MoveTo move = new MoveTo(convertX(oldModel.getNextPos()[0]),
        convertY(oldModel.getNextPos()[1]));
    Path path = new Path();
    path.getElements().addAll(move,
        new LineTo(convertX(myModel.getNextPos()[0]), convertY(myModel.getNextPos()[1])));
    Duration pathAnimDuration;
    if (o.isPresent() && moved(oldModel.getNextPos())) {
      pathAnimDuration = Duration.seconds(Math.abs((int) o.get()) / TURTLE_SPEED);
      return new PathTransition(pathAnimDuration, path, turtle);
    } else {
      return doNothingPath(oldModel);
    }
  }

  private PathTransition doNothingPath(TurtleModel oldModel) {
    MoveTo move = new MoveTo(convertX(oldModel.getNextPos()[0]),
        convertY(oldModel.getNextPos()[1]));
    Path path = new Path();
    path.getElements().addAll(move,
        new LineTo(convertX(myModel.getNextPos()[0]), convertY(myModel.getNextPos()[1] - 1)),
        new LineTo(convertX(oldModel.getNextPos()[0]), convertY(oldModel.getNextPos()[1])));

    return new PathTransition(Duration.seconds(NO_MOVEMENT), path, turtle);
  }

  private RotateTransition doNothingRotate() {
    RotateTransition rt = new RotateTransition();
    rt.setNode(turtle);
    rt.setDuration(Duration.seconds(NO_MOVEMENT));
    rt.setByAngle(0);
    return rt;
  }

  private boolean moved(double[] nextPos) {
    return (nextPos[0] != myModel.getNextPos()[0] || nextPos[1] != myModel.getNextPos()[1]);
  }

//  private PathTransition getPathTransition(Optional<Object> o, TurtleModel oldModel)
//      throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
//    MoveTo move = new MoveTo(convertX(oldModel.getNextPos()[0]), convertY(oldModel.getNextPos()[1]));
//    Path path = new Path();
//    Duration pathAnimDuration;
//    if (o.isPresent()) {
//      path.getElements().addAll(move,
//          new LineTo(convertX(myModel.getNextPos()[0]), convertY(myModel.getNextPos()[1])));
//      // create an animation where the shape follows a path
//      pathAnimDuration = Duration.seconds((int) o.get() / TURTLE_SPEED);
//    } else {
//      pathAnimDuration = Duration.seconds(NO_MOVEMENT);
//
//    }
//    return new PathTransition(pathAnimDuration, path, turtle);
//  }

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
