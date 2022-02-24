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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Rotate;
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

  public Scene makeScene () {
    Group root = new Group();
    root.getChildren().add((Node) turtle);
    return new Scene(root, DEFAULT_SIZE.width, DEFAULT_SIZE.height);

  }

  public TurtleView makeTurtle() {
    return new TurtleView(convertX(myModel.getNextPos()[0]), convertY(myModel.getNextPos()[1]),
            myModel.getHeading(), "turtle", Color.RED) {
//      @Override
//      public void updateTurtleView() {
//
//      }

      @Override
      public void updateTurtle(double x, double y, double heading, Color color) {

      }

      @Override
      public void updateTurtleView() {

      }
    };
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


  /*
   TODO: CREATE PATH AND ROTATION BASED ON WHAT IS CHANGED (MAKE BOTH AND PASS THE
    SEQUENTIAL TRANSITION WITH BOTH IN IT
   todo:
    figure out how all this ties in with turtleView
  */
//  public Animation makeAnimation ()
//      throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
//    // create something to follow
//    TurtleModel oldModelState = myModel;
////    MoveTo move = new MoveTo(convertX(myModel.getNextPos()[0]), convertY(myModel.getNextPos()[1]));
//    Optional<Object> o = myModel.runNextInsn();
////    PathTransition pt = getPathTransition(o, move);
////    RotateTransition rt = new RotateTransition(Duration.seconds(3));
////    rt.setByAngle(-90);
////    return pt;
//    Transition t;
//    Optional<Method> m = getChangeFunction(oldModelState);
//    if (m.isPresent()) {
//      Object ret = m.get().invoke(this, o, oldModelState);
//      t = (Transition) ret;
//    }
//    else {
//      //t = new PathTransition(Duration.seconds(NO_MOVEMENT))
//      t = new RotateTransition(Duration.seconds(NO_MOVEMENT));
//    }
//    return new SequentialTransition(turtle, t);
//
//    //RotateTransition rt = getRotateTransition();
////    MoveTo move = new MoveTo(convertX(myModel.getNextPos()[0]), convertY(myModel.getNextPos()[1]));
////    Path path = new Path();
////    Optional<Object> o = myModel.runNextInsn();
////    Duration currAnimDuration;
////    if (o.isPresent()) {
////      path.getElements().addAll(move,
////          new LineTo(convertX(myModel.getNextPos()[0]), convertY(myModel.getNextPos()[1])));
////      // create an animation where the shape follows a path
////      currAnimDuration = Duration.seconds((int) o.get() / TURTLE_SPEED);
////    } else {
////      currAnimDuration = Duration.seconds(NO_MOVEMENT);
////
////    }
////    return new PathTransition(currAnimDuration, path, turtle);
//
////    // create an animation that rotates the shape
////    RotateTransition rt = new RotateTransition(Duration.seconds(3));
////    rt.setByAngle(-90);
////    // put them together in order
//    //return new SequentialTransition(turtle, pt, rt);
//  }

  public Animation makeAnimation ()
      throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
    // create something to follow
    //TurtleModel oldModelState = myModel;
    MoveTo move = new MoveTo(convertX(myModel.getNextPos()[0]), convertY(myModel.getNextPos()[1]));
    Optional<Object> o = myModel.runNextInsn();
    PathTransition pt = getPathTransition(o, move);
    RotateTransition rt = new RotateTransition(Duration.seconds(3));
    rt.setByAngle(-90);
    return pt;


//    Transition t;
//    Optional<Method> m = getChangeFunction(oldModelState);
//    if (m.isPresent()) {
//      Object ret = m.get().invoke(this, o, oldModelState, move);
//      t = (Transition) ret;
//    }
//    else {
//      //t = new PathTransition(Duration.seconds(NO_MOVEMENT))
//      t = new RotateTransition(Duration.seconds(NO_MOVEMENT));
//    }
//    return new SequentialTransition(turtle, t);

    //RotateTransition rt = getRotateTransition();
//    MoveTo move = new MoveTo(convertX(myModel.getNextPos()[0]), convertY(myModel.getNextPos()[1]));
//    Path path = new Path();
//    Optional<Object> o = myModel.runNextInsn();
//    Duration currAnimDuration;
//    if (o.isPresent()) {
//      path.getElements().addAll(move,
//          new LineTo(convertX(myModel.getNextPos()[0]), convertY(myModel.getNextPos()[1])));
//      // create an animation where the shape follows a path
//      currAnimDuration = Duration.seconds((int) o.get() / TURTLE_SPEED);
//    } else {
//      currAnimDuration = Duration.seconds(NO_MOVEMENT);
//
//    }
//    return new PathTransition(currAnimDuration, path, turtle);

//    // create an animation that rotates the shape
//    RotateTransition rt = new RotateTransition(Duration.seconds(3));
//    rt.setByAngle(-90);
//    // put them together in order
    //return new SequentialTransition(turtle, pt, rt);
  }

  private Optional<Method> getChangeFunction(TurtleModel oldModel)
      throws NoSuchMethodException {
    if (!oldModel.getNextPos().equals(myModel.getNextPos())) {
      return Optional.of(this.getClass().getDeclaredMethod("getPathTransition", Optional.class, TurtleModel.class, MoveTo.class));
    }
    else if (!(oldModel.getHeading() == myModel.getHeading())) {
      return Optional.of(this.getClass().getDeclaredMethod("getRotateTransition", Optional.class, TurtleModel.class));
    }
    return Optional.empty();
  }

  private RotateTransition getRotateTransition(Optional<Object> o, TurtleModel oldModel) {
    RotateTransition rt = new RotateTransition();
    if (o.isPresent()) {
      rt.setDuration(Duration.seconds((int) o.get() / TURTLE_TURN_SPEED));
      rt.setByAngle(-1*(double)o.get());
    } else {
      rt.setDuration(Duration.seconds(NO_MOVEMENT));
      rt.setByAngle(0);
    }
    return rt;
  }

  private PathTransition getPathTransition(Optional<Object> o, MoveTo move)
      throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
    //MoveTo move = new MoveTo(convertX(oldModel.getNextPos()[0]), convertY(oldModel.getNextPos()[1]));
    Path path = new Path();
    Duration pathAnimDuration;
    if (o.isPresent()) {
      path.getElements().addAll(move,
          new LineTo(convertX(myModel.getNextPos()[0]), convertY(myModel.getNextPos()[1])));
      // create an animation where the shape follows a path
      pathAnimDuration = Duration.seconds((int) o.get() / TURTLE_SPEED);
    } else {
      pathAnimDuration = Duration.seconds(NO_MOVEMENT);

    }
    return new PathTransition(pathAnimDuration, path, turtle);
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
