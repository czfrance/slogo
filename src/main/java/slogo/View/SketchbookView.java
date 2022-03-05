package slogo.View;

import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import slogo.Model.TurtleInsnModel;
import slogo.Model.TurtleModel;
import slogo.View.Pen.Pen;

public class SketchbookView extends Region {

  public static final Dimension DEFAULT_SIZE = new Dimension(400, 400);
  public static int TURTLE_SPEED = 50; //pixels per second
  public static final int TURTLE_TURN_SPEED = 45; //degrees per second
  public static final double NO_MOVEMENT = 0.01; //pixels per second

  Pane myPane;
  Map<TurtleModel, TurtleView> myTurtlesMap;
  private SimulationDisplay mySimulation;
  private BorderPane myRoot;
  //private TurtleModel myCurrModel;
  private TurtleInsnModel myInsnModel;
  private Group root;
  private boolean isAnimated;


  public SketchbookView(TurtleInsnModel insnModel) {
    myInsnModel = insnModel;
    //myCurrModel = myInsnModel.getCurrTurtle();
    myTurtlesMap = createTurtleMap();
  }

  public Group makeScene() {
    root = new Group();
    addTurtlesToRoot();
    return root;
    //return new Scene(root, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
  }

//  public Scene makeScene(BorderPane myFeatures) {
//    root = new Group();
//    root.getChildren().add(myFeatures);
//    return new Scene(root, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
//  }

  private Map<TurtleModel, TurtleView> createTurtleMap() {
    Map<TurtleModel, TurtleView> turtlesMap = new HashMap<>();
    Map<Integer, TurtleModel> turtleModelsMap = myInsnModel.getCreatedTurtleMap();
    for (int i : turtleModelsMap.keySet()) {
      TurtleView newTurtle = makeTurtle(turtleModelsMap.get(i));
      turtlesMap.put(turtleModelsMap.get(i), newTurtle);
    }
    return turtlesMap;
  }

  private void addTurtlesToRoot() {
    for (TurtleModel m : myTurtlesMap.keySet()) {
      root.getChildren().add(myTurtlesMap.get(m));
    }
  }

  private void updateTurtleMap() {
    Map<Integer, TurtleModel> turtleModelsMap = myInsnModel.getCreatedTurtleMap();
    if (!myTurtlesMap.keySet().containsAll(turtleModelsMap.values())) {
      List<TurtleModel> newModels = getNewModels(turtleModelsMap);
      for (TurtleModel m : newModels) {
        TurtleView turtle = makeTurtle(m);
        myTurtlesMap.put(m, turtle);
        root.getChildren().add(turtle);
      }
    }
  }

  private List<TurtleModel> getNewModels(Map<Integer, TurtleModel> turtleModelsMap) {
    List<TurtleModel> newModels = new ArrayList<>();
    for (TurtleModel m : turtleModelsMap.values()) {
      if (!myTurtlesMap.containsKey(m)) {
        newModels.add(m);
      }
    }
    return newModels;
  }

  private TurtleView makeTurtle(TurtleModel m) {
    return new TurtleDisplay(convertX(m.getNextPos()[0]), convertY(m.getNextPos()[1]),
            m.getHeading(), "turtle", Color.RED);
  }

  public void play() {
    // displayWindow();
    try {
      //updateCurrTurtle();
      updateTurtleMap();
      Animation animation = makeAnimation();
      //checkShowing();
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
    List<Integer> activeIDList = myInsnModel.getActiveIDList();
    List<TurtleModel> activeTurtles= new ArrayList<TurtleModel>();
    List<TurtleModel> oldActiveTurtles = new ArrayList<TurtleModel>();
    for(int i: activeIDList) {
      TurtleModel currActiveTurtle = myInsnModel.getCreatedTurtleMap().get(i);
      TurtleModel oldActiveTurtle = new TurtleModel(currActiveTurtle.getNextPos()[0], currActiveTurtle.getNextPos()[1],
          currActiveTurtle.getHeading());
      activeTurtles.add(currActiveTurtle);
      oldActiveTurtles.add(oldActiveTurtle);
    }
    //TurtleModel oldModelState = new TurtleModel(myCurrModel.getNextPos()[0], myCurrModel.getNextPos()[1],
     //   myCurrModel.getHeading());

    Optional<Object> o = myInsnModel.runNextInsn();
    //myCurrModel = myInsnModel.getCurrTurtle();
    if (o.isPresent()) {
      return getTransition(o, oldActiveTurtles, activeTurtles);
    } else {
      return doNothingPath(oldActiveTurtles.get(0), activeTurtles.get(0));
    }
  }

  private Transition getTransition(Optional<Object> o, List<TurtleModel> oldModel, List<TurtleModel> newModel)
      throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
    SequentialTransition transition = new SequentialTransition();
    for(int i = 0; i<oldModel.size(); i++) {
      //System.out.printf("CurrTurtle Transition ID %s\n", myInsnModel.getActiveIDList().get(i));
      PathTransition pt = getPathTransition(o, oldModel.get(i), newModel.get(i));
      RotateTransition rt = getRotateTransition(o, oldModel.get(i), newModel.get(i));
      transition.getChildren().addAll(pt, rt);

    }
    return transition;
    //return new SequentialTransition(myTurtlesMap.get(myCurrModel), pt, rt);
  }

  /*
  private Optional<Method> getChangeFunction(TurtleModel oldModel)
      throws NoSuchMethodException {
    if (!oldModel.getNextPos().equals(myCurrModel.getNextPos())) {
      return Optional.of(this.getClass()
          .getDeclaredMethod("getPathTransition", Optional.class, TurtleModel.class, MoveTo.class));
    } else if (!(oldModel.getHeading() == myCurrModel.getHeading())) {
      return Optional.of(this.getClass()
          .getDeclaredMethod("getRotateTransition", Optional.class, TurtleModel.class));
    }
    return Optional.empty();
  }

   */

  private RotateTransition getRotateTransition(Optional<Object> o, TurtleModel oldModel, TurtleModel myCurrModel) {
    RotateTransition rt = new RotateTransition();
    rt.setNode(myTurtlesMap.get(myCurrModel));
    if (o.isPresent() && !(oldModel.getHeading() == myCurrModel.getHeading())) {
      double angle = (double) o.get();
      rt.setDuration(Duration.seconds(Math.abs(angle) / TURTLE_TURN_SPEED));
      rt.setByAngle(angle);
      return rt;
    } else {
      return doNothingRotate(myCurrModel);
    }
  }

  private PathTransition getPathTransition(Optional<Object> o, TurtleModel oldModel, TurtleModel myCurrModel)
      throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
    MoveTo move = new MoveTo(convertX(oldModel.getNextPos()[0]),
        convertY(oldModel.getNextPos()[1]));
    Path path = new Path();
    path.getElements().addAll(move,
        new LineTo(convertX(myCurrModel.getNextPos()[0]), convertY(myCurrModel.getNextPos()[1])));
    Duration pathAnimDuration;
    if (o.isPresent() && moved(oldModel.getNextPos(), myCurrModel)) {
      pathAnimDuration = Duration.seconds(Math.abs((double) o.get()) / TURTLE_SPEED);
      PathTransition pathTrans = new PathTransition(pathAnimDuration, path, myTurtlesMap.get(
          myCurrModel));
      setListener(pathTrans);
      return pathTrans;
    } else {
      return doNothingPath(oldModel, myCurrModel);
    }
  }

  private void setListener(PathTransition pathTrans) {
    pathTrans.currentTimeProperty().addListener( new ChangeListener<Duration>() {

      double[] oldLocation = null;

      /**
       * Draw a line from the old location to the new location
       */
      @Override
      public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {

        for(int i: myInsnModel.getActiveIDList()) {
          TurtleModel myCurrModel = myInsnModel.getCreatedTurtleMap().get(i);
          // skip starting at 0/0
          if (oldValue == Duration.ZERO)
            return;

          // get current location
          double x = myTurtlesMap.get(myCurrModel).getBoundsInParent().getCenterX();
          double y = myTurtlesMap.get(myCurrModel).getBoundsInParent().getCenterY();

          // initialize the location
          if (oldLocation == null) {
            oldLocation = new double[]{x, y};
            return;
          }

          // draw line
          if (myCurrModel.getTurtleRecord().isPenDown()) {
            root.getChildren().add(
                myTurtlesMap.get(myCurrModel).getPen().draw(oldLocation[0], oldLocation[1], x, y));
          }

          // update old location with current one
          oldLocation[0] = x;
          oldLocation[1] = y;
        }
      }
    });
  }

  /*
  private void checkShowing() {
    if (!myCurrModel.getTurtleRecord().isShowing())  {
      root.getChildren().remove(myTurtlesMap.get(myCurrModel));
    }
    else if (!root.getChildren().contains(myTurtlesMap.get(myCurrModel))){
      root.getChildren().add(myTurtlesMap.get(myCurrModel));
    }
  }

   */

  private PathTransition doNothingPath(TurtleModel oldModel, TurtleModel myCurrModel) {
    MoveTo move = new MoveTo(convertX(oldModel.getNextPos()[0]),
        convertY(oldModel.getNextPos()[1]));
    Path path = new Path();
    path.getElements().addAll(move,
        new LineTo(convertX(myCurrModel.getNextPos()[0]), convertY(myCurrModel.getNextPos()[1] - 1)),
        new LineTo(convertX(oldModel.getNextPos()[0]), convertY(oldModel.getNextPos()[1])));

    return new PathTransition(Duration.seconds(NO_MOVEMENT), path, myTurtlesMap.get(myCurrModel));
  }

  private RotateTransition doNothingRotate(TurtleModel myCurrModel) {
    RotateTransition rt = new RotateTransition();
    rt.setNode(myTurtlesMap.get(myCurrModel));
    rt.setDuration(Duration.seconds(NO_MOVEMENT));
    rt.setByAngle(0);
    return rt;
  }

  private boolean moved(double[] nextPos, TurtleModel myCurrModel) {
    return (nextPos[0] != myCurrModel.getNextPos()[0] || nextPos[1] != myCurrModel.getNextPos()[1]);
  }

  private double convertX(double modelX) {
    return modelX + (DEFAULT_SIZE.width / 2);
  }

  private double convertY(double modelY) {
    return (DEFAULT_SIZE.height / 2) - modelY;
  }

  /*
  public void updateCurrTurtle() {
    double x = myTurtlesMap.get(myCurrModel).getBoundsInParent().getCenterX();
    double y = myTurtlesMap.get(myCurrModel).getBoundsInParent().getCenterY();
    double heading = myCurrModel.getHeading();
    Color color = myTurtlesMap.get(myCurrModel).getColor();
    myTurtlesMap.get(myCurrModel).updateTurtle(x, y, heading, color);
  }

   */

//    @Override
//    public Node getStyleableNode() {
//        return super.getStyleableNode();
//    }

  public void pause() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
    if (!isAnimated) {
      isAnimated = true;
      makeAnimation();
    }
  }

  public void setAnimationSpeed(Number newValue) {
    TURTLE_SPEED = (int) newValue;
  }

  public void reset() {
    makeScene();
    //makeScene(myRoot);
    isAnimated = false;
    //updateGridPane();
  }

  public Pane getPane() {
    return myPane;
  }

  public BorderPane getBorderPane() {
    return myRoot;
  }

  @Override
  public Node getStyleableNode() {
    return super.getStyleableNode();
  }
}
