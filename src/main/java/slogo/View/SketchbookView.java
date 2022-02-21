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

    public Animation makeAnimation ()
      throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
      // create something to follow
      System.out.println("yay");
      TurtleModel oldModelState = new TurtleModel(myModel.getNextPos()[0], myModel.getNextPos()[1],
          myModel.getHeading());
      //MoveTo move = new MoveTo(convertX(myModel.getNextPos()[0]), convertY(myModel.getNextPos()[1]));
      Optional<Object> o = myModel.runNextInsn();
      if (o.isPresent()) {
        return getTransition(o, oldModelState);
      } else {
        return doNothing(oldModelState);
      }

//    PathTransition pt = getPathTransition(o, move);
//    RotateTransition rt = new RotateTransition(Duration.seconds(3));
//    rt.setByAngle(-90);
//    return pt;
//    PathTransition pt = getPathTransition(o, move);
//    RotateTransition rt = getRotateTransition(o);
//    return getTransition(o, move);
//    Optional<Method> m = getChangeFunction(oldModelState);
//    if (m.isPresent()) {
//      Object ret = m.get().invoke(this, o, oldModelState);
//      t = (Transition) ret;
//    }
//    else {
//      //t = new PathTransition(Duration.seconds(NO_MOVEMENT))
//      t = new RotateTransition(Duration.seconds(NO_MOVEMENT));
//    }
//    return new SequentialTransition(turtle, pt, rt);
  }
  private PathTransition doNothing(TurtleModel oldModel) {
    MoveTo move = new MoveTo(convertX(oldModel.getNextPos()[0]), convertY(oldModel.getNextPos()[1]));
    Path path = new Path();
    path.getElements().addAll(move,
        new LineTo(convertX(myModel.getNextPos()[0]), convertY(myModel.getNextPos()[1]-1)),
        new LineTo(convertX(oldModel.getNextPos()[0]), convertY(oldModel.getNextPos()[1])));

    return new PathTransition(Duration.seconds(NO_MOVEMENT), path, turtle);
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

  //rotating
//  public Animation makeAnimation ()
//      throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
//    // create something to follow
//    //TurtleModel oldModelState = myModel;
//    Optional<Object> o = myModel.runNextInsn();
//    int angle = (int) o.get();
//    RotateTransition rt = new RotateTransition(Duration.seconds(Math.abs(angle) / TURTLE_TURN_SPEED), turtle);
//    System.out.println(angle);
//    rt.setByAngle(angle);
//
////    RotateTransition rt = new RotateTransition(Duration.seconds(3), turtle);
////    System.out.println(angle);
////    rt.setByAngle(-90);
//    return rt;
//  }

//  public Animation makeAnimation ()
//      throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
//    // create something to follow
//    //TurtleModel oldModelState = myModel;
//    MoveTo move = new MoveTo(convertX(myModel.getNextPos()[0]), convertY(myModel.getNextPos()[1]));
//    Optional<Object> o = myModel.runNextInsn();
//    PathTransition pt = getPathTransition(o, move);
//    RotateTransition rt = new RotateTransition(Duration.seconds(3));
//    rt.setByAngle(-90);
//    return pt;
//  }

  private Transition getTransition(Optional<Object> o, TurtleModel oldModel)
      throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
//    MoveTo move = new MoveTo(convertX(myModel.getNextPos()[0]), convertY(myModel.getNextPos()[1]));
//    //return getPathTransition(o, move);
//    Path p = new Path();
//    p.getElements().addAll(move,
//        new LineTo(convertX(myModel.getNextPos()[0]), convertY(myModel.getNextPos()[1])));
//    PathTransition pt = new PathTransition(Duration.seconds(NO_MOVEMENT), p, turtle);
//    RotateTransition rt = new RotateTransition(Duration.seconds(NO_MOVEMENT), turtle);
//    if (!oldModel.getNextPos().equals(myModel.getNextPos())) {
//      pt = getPathTransition(o, oldModel);
//      //pt = getPathTransition(o, move);
//    }
//     else if (!(oldModel.getHeading() == myModel.getHeading())) {
//      rt = getRotateTransition(o);
//    }
    PathTransition pt = getPathTransition(o, oldModel);
    RotateTransition rt = getRotateTransition(o, oldModel);
    return new SequentialTransition(turtle, pt, rt);
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

  private RotateTransition getRotateTransition(Optional<Object> o, TurtleModel oldModel) {//, TurtleModel oldModel) {
    RotateTransition rt = new RotateTransition();
    rt.setNode(turtle);
    if (o.isPresent() && !(oldModel.getHeading() == myModel.getHeading())) {
      int angle = (int)o.get();
      rt.setDuration(Duration.seconds(Math.abs(angle) / TURTLE_TURN_SPEED));
      rt.setByAngle(angle);
    } else {
      rt.setDuration(Duration.seconds(NO_MOVEMENT));
      rt.setByAngle(0);
    }
    return rt;
  }

  private PathTransition getPathTransition(Optional<Object> o, TurtleModel oldModel)
      throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
    MoveTo move = new MoveTo(convertX(oldModel.getNextPos()[0]), convertY(oldModel.getNextPos()[1]));
    Path path = new Path();
    path.getElements().addAll(move,
        new LineTo(convertX(myModel.getNextPos()[0]), convertY(myModel.getNextPos()[1])));
    Duration pathAnimDuration;
    System.out.println(o.isPresent());
    if (o.isPresent() && moved(oldModel.getNextPos())) {
      System.out.println("yes");
      // create an animation where the shape follows a path
      pathAnimDuration = Duration.seconds(Math.abs((int) o.get()) / TURTLE_SPEED);
      return new PathTransition(pathAnimDuration, path, turtle);
    } else {
      return doNothing(oldModel);
      //pathAnimDuration = Duration.seconds(NO_MOVEMENT);
    }

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
